package org.example;

import java.sql.*;
import java.io.File;

public class DatabaseConnection {
    // Путь к базе данных в директории проекта
    private static final String DB_DIRECTORY = "./database/";
    private static final String DB_FILE = "vegetables.accdb";
    private static final String DB_PATH = DB_DIRECTORY + DB_FILE;
    private static final String ACCESS_URL = "jdbc:ucanaccess://" + DB_PATH;

    // Для тестов (H2)
    private static final String TEST_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        createDatabaseIfNotExists();
        return DriverManager.getConnection(ACCESS_URL, USER, PASSWORD);
    }

    // Для тестов
    public static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection(TEST_URL, USER, PASSWORD);
    }

    // Создает базу данных и папку если не существуют
    private static void createDatabaseIfNotExists() {
        try {
            File dbDir = new File(DB_DIRECTORY);
            File dbFile = new File(DB_PATH);
            // Создаем папку если не существует
            if (!dbDir.exists()) {
                dbDir.mkdirs();
                System.out.println("Created database directory: " + dbDir.getAbsolutePath());
            }
            // Создаем базу данных если не существует
            if (!dbFile.exists()) {
                // Создаем временное соединение для создания файла БД
                String tempUrl = "jdbc:ucanaccess://" + DB_PATH + ";newdatabaseversion=V2003";
                Connection tempConn = DriverManager.getConnection(tempUrl, USER, PASSWORD);
                tempConn.close();
                System.out.println("Created new database: " + dbFile.getAbsolutePath());
            }
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }

    public static void createTable() throws SQLException {
        String sql = """
            CREATE TABLE Vegetables (
                ID AUTOINCREMENT PRIMARY KEY,
                Name VARCHAR(100) NOT NULL,
                Calories INTEGER NOT NULL
            )
            """;
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {
            // Удаляем таблицу если существует
            try {
                statement.execute("DROP TABLE Vegetables");
            } catch (SQLException e) {
                // Таблицы не существует, это нормально
            }
            statement.execute(sql);
            System.out.println("Table created successfully");
        }
    }

    // Для тестов (H2 использует INT AUTO_INCREMENT вместо AUTOINCREMENT)
    public static void createTestTable() throws SQLException {
        String sql = """
            CREATE TABLE Vegetables (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                Name VARCHAR(100) NOT NULL,
                Calories INTEGER NOT NULL
            )
            """;
        try (Connection conn = getTestConnection();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
        }
    }

    // Метод для получения пути к БД (для информации)
    public static String getDatabasePath() {
        return new File(DB_PATH).getAbsolutePath();
    }
}
