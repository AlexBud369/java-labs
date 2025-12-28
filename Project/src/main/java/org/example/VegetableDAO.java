package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VegetableDAO {
    private Connection externalConnection;
    private boolean isExternalConnection;

    public VegetableDAO() {
        this.externalConnection = null;
        this.isExternalConnection = false;
    }

    public VegetableDAO(Connection connection) {
        this.externalConnection = connection;
        this.isExternalConnection = true;
    }

    private Connection getConnection() throws SQLException {
        if (isExternalConnection && externalConnection != null) {
            return externalConnection;
        }
        return DatabaseConnection.getConnection();
    }

    private void closeIfNeeded(Connection conn) {
        if (!isExternalConnection && conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void create(Vegetable veg) {
        Connection conn = null;
        String sql = "INSERT INTO Vegetables (Name, Calories) VALUES (?, ?)";

        try {
            conn = getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, veg.getName());
                pstmt.setInt(2, veg.getCalories());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    public List<Vegetable> readAll() {
        List<Vegetable> veggies = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT * FROM Vegetables";

        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    veggies.add(new Vegetable(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getInt("Calories")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return veggies;
    }

    public Vegetable readById(int id) {
        Connection conn = null;
        String sql = "SELECT * FROM Vegetables WHERE ID = ?";

        try {
            conn = getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new Vegetable(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getInt("Calories"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return null;
    }

    public void update(Vegetable veg) {
        Connection conn = null;
        String sql = "UPDATE Vegetables SET Name = ?, Calories = ? WHERE ID = ?";

        try {
            conn = getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, veg.getName());
                pstmt.setInt(2, veg.getCalories());
                pstmt.setInt(3, veg.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    public void delete(int id) {
        Connection conn = null;
        String sql = "DELETE FROM Vegetables WHERE ID = ?";

        try {
            conn = getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    public int calculateSaladCalories() {
        Connection conn = null;
        int total = 0;
        String sql = "SELECT SUM(Calories) FROM Vegetables";

        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return total;
    }

    public List<Vegetable> sortByCalories() {
        List<Vegetable> veggies = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT * FROM Vegetables ORDER BY Calories ASC";

        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    veggies.add(new Vegetable(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getInt("Calories")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return veggies;
    }

    public List<Vegetable> findByCaloriesRange(int min, int max) {
        List<Vegetable> veggies = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT * FROM Vegetables WHERE Calories BETWEEN ? AND ?";

        try {
            conn = getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, min);
                pstmt.setInt(2, max);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        veggies.add(new Vegetable(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getInt("Calories")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return veggies;
    }

    public void close() {
        if (isExternalConnection && externalConnection != null) {
            try {
                externalConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}