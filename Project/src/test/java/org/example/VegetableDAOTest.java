package org.example;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VegetableDAOTest {
    private Connection testConnection;
    private VegetableDAO vegetableDAO;

    @BeforeAll
    void setUp() throws Exception {
        // Используем H2 для тестов
        testConnection = DatabaseConnection.getTestConnection();
        DatabaseConnection.createTestTable();
    }

    @BeforeEach
    void init() throws SQLException {
        // Важно: не закрываем statement отдельно от соединения
        // Просто очищаем таблицу
        String sql = "DELETE FROM Vegetables";
        try (PreparedStatement pstmt = testConnection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }

        // Создаем DAO с тестовым соединением
        vegetableDAO = new VegetableDAO(testConnection);
    }

    @AfterAll
    void tearDown() throws SQLException {
        if (testConnection != null && !testConnection.isClosed()) {
            testConnection.close();
        }
    }

    @Test
    void testCreate() throws SQLException {
        // Given
        var veg = new Vegetable(0, "Test Veg", 50);
        // When
        vegetableDAO.create(veg);
        // Then
        var veggies = vegetableDAO.readAll();
        assertEquals(1, veggies.size());
        assertEquals("Test Veg", veggies.get(0).getName());
        assertEquals(50, veggies.get(0).getCalories());
    }

    @Test
    void testReadAll() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "Veg1", 10));
        vegetableDAO.create(new Vegetable(0, "Veg2", 20));
        // When
        List<Vegetable> veggies = vegetableDAO.readAll();
        // Then
        assertEquals(2, veggies.size());
    }

    @Test
    void testReadById() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "Test Veg", 50));
        var allVeggies = vegetableDAO.readAll();
        var vegId = allVeggies.get(0).getId();
        // When
        var foundVeg = vegetableDAO.readById(vegId);
        // Then
        assertNotNull(foundVeg);
        assertEquals("Test Veg", foundVeg.getName());
        assertEquals(50, foundVeg.getCalories());
    }

    @Test
    void testUpdate() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "Old Name", 30));
        var allVeggies = vegetableDAO.readAll();
        var vegToUpdate = allVeggies.get(0);
        var updatedVeg = new Vegetable(vegToUpdate.getId(), "New Name", 40);
        // When
        vegetableDAO.update(updatedVeg);
        // Then
        var result = vegetableDAO.readById(vegToUpdate.getId());
        assertEquals("New Name", result.getName());
        assertEquals(40, result.getCalories());
    }

    @Test
    void testDelete() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "To Delete", 60));
        var allVeggies = vegetableDAO.readAll();
        var vegId = allVeggies.get(0).getId();
        // When
        vegetableDAO.delete(vegId);
        // Then
        var veggiesAfterDelete = vegetableDAO.readAll();
        assertTrue(veggiesAfterDelete.isEmpty());
    }

    @Test
    void testReadById_NotFound() throws SQLException {
        // When
        var result = vegetableDAO.readById(999);
        // Then
        assertNull(result);
    }

    @Test
    void testCalculateSaladCalories() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "Veg1", 10));
        vegetableDAO.create(new Vegetable(0, "Veg2", 20));
        // When
        int total = vegetableDAO.calculateSaladCalories();
        // Then
        assertEquals(30, total);
    }

    @Test
    void testSortByCalories() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "VegHigh", 30));
        vegetableDAO.create(new Vegetable(0, "VegLow", 10));
        // When
        List<Vegetable> sorted = vegetableDAO.sortByCalories();
        // Then
        assertEquals(2, sorted.size());
        assertEquals(10, sorted.get(0).getCalories());  // Низкий первый
        assertEquals(30, sorted.get(1).getCalories());
    }

    @Test
    void testFindByCaloriesRange() throws SQLException {
        // Given
        vegetableDAO.create(new Vegetable(0, "Veg1", 5));   // Вне диапазона
        vegetableDAO.create(new Vegetable(0, "Veg2", 15));  // В диапазоне
        vegetableDAO.create(new Vegetable(0, "Veg3", 25));  // В диапазоне
        vegetableDAO.create(new Vegetable(0, "Veg4", 35));  // Вне
        // When
        List<Vegetable> inRange = vegetableDAO.findByCaloriesRange(10, 30);
        // Then
        assertEquals(2, inRange.size());
        assertTrue(inRange.stream().anyMatch(v -> v.getCalories() == 15));
        assertTrue(inRange.stream().anyMatch(v -> v.getCalories() == 25));
    }
}