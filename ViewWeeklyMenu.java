package service;

import repository.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewWeeklyMenu {

    public static void viewWeeklyMenu() {
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT day, menu_item FROM weekly_menu";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("Weekly Menu:");
                while (rs.next()) {
                    String day = rs.getString("day");
                    String menuItem = rs.getString("menu_item");
                    System.out.println(day + ": " + menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
