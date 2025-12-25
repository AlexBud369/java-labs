package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VegetableDAO {
    private Connection connection;  // Для тестов (опционально)

    // Дефолтный конструктор для реальной БД
    public VegetableDAO() {
        this.connection = null;
    }

    // Конструктор для тестов (с переданным соединением, например, H2)
    public VegetableDAO(Connection connection) {
        this.connection = connection;
    }

    // Вспомогательный метод для получения соединения (реальное или тестовое)
    private Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;  // Для тестов
        }
        return DatabaseConnection.getConnection();  // Для реальной Access
    }

    // Create
    public void create(Vegetable veg) {
        String sql = "INSERT INTO Vegetables (Name, Calories) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, veg.getName());
            pstmt.setInt(2, veg.getCalories());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all
    public List<Vegetable> readAll() {
        List<Vegetable> veggies = new ArrayList<>();
        String sql = "SELECT * FROM Vegetables";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                veggies.add(new Vegetable(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Calories")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veggies;
    }

    // Read by ID (новый метод)
    public Vegetable readById(int id) {
        String sql = "SELECT * FROM Vegetables WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Vegetable(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Calories"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Не найден
    }

    // Update
    public void update(Vegetable veg) {
        String sql = "UPDATE Vegetables SET Name = ?, Calories = ? WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, veg.getName());
            pstmt.setInt(2, veg.getCalories());
            pstmt.setInt(3, veg.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM Vegetables WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Подсчёт калорийности салата (сумма всех)
    public int calculateSaladCalories() {
        int total = 0;
        String sql = "SELECT SUM(Calories) FROM Vegetables";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Сортировка по калорийности (ASC)
    public List<Vegetable> sortByCalories() {
        List<Vegetable> veggies = new ArrayList<>();
        String sql = "SELECT * FROM Vegetables ORDER BY Calories ASC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                veggies.add(new Vegetable(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Calories")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veggies;
    }

    // Поиск по диапазону калорий
    public List<Vegetable> findByCaloriesRange(int min, int max) {
        List<Vegetable> veggies = new ArrayList<>();
        String sql = "SELECT * FROM Vegetables WHERE Calories BETWEEN ? AND ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, min);
            pstmt.setInt(2, max);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    veggies.add(new Vegetable(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Calories")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veggies;
    }

    // Close (если нужно закрыть постоянное соединение; здесь опционально)
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}