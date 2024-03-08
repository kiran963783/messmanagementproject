package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import repository.DatabaseConnector;

public class RegisterCandidate {

    public static void registerCandidate(Scanner scanner) {
        System.out.print("Is the candidate monthly? (yes/no): ");
        String isMonthly = scanner.nextLine();

        if (!isMonthly.equalsIgnoreCase("yes")) {
            System.out.println("Only candidates in the monthly category can be registered.");
            return; 
        }

        System.out.println("Enter candidate details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Advance amount: ");
        double advanceAmount = scanner.nextDouble();
        scanner.nextLine();

        String category = "Monthly"; 

        
        try (Connection conn = DatabaseConnector.connect()) {
            if (conn != null) {
                insertCandidate(conn, name, phone, email, address, advanceAmount, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertCandidate(Connection conn, String name, String phone, String email,
                                        String address, double advanceAmount, String category) {
        String sql = "INSERT INTO candidates (name, phone, email, address, advance_amount, category) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setDouble(5, advanceAmount);
            pstmt.setString(6, category);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Candidate registered successfully.");
            } else {
                System.out.println("Failed to register candidate.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
