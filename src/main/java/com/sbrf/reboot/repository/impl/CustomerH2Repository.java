package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "ivanig";
    private final String PASS = "";

    public CustomerH2Repository() {
        try (Connection conn = getConnection()
                .orElseThrow(() -> new CanNotConnectException("Unable to establish connection."))) {
            String sql = "CREATE TABLE IF NOT EXISTS Customer (ID IDENTITY PRIMARY KEY," +
                    " NAME VARCHAR(20) NOT NULL, EMAIL VARCHAR(30));";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.execute();
            }
        } catch (SQLException | CanNotConnectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = getConnection()
                .orElseThrow(() -> new CanNotConnectException("Unable to establish connection."))) {
            String sql = "SELECT * FROM Customer";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new Customer(resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email")));
                    }
                }
            }
        } catch (SQLException | CanNotConnectException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        try (Connection conn = getConnection()
                .orElseThrow(() -> new CanNotConnectException("Unable to establish connection."))) {
            String sql = "INSERT INTO Customer (Name, Email) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, eMail);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException | CanNotConnectException e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIfCustomerExist(String name) {
        boolean isResultNotEmpty = false;
        try (Connection conn = getConnection()
                .orElseThrow(() -> new CanNotConnectException("Unable to establish connection."))) {
            String sql = "SELECT name FROM Customer WHERE name = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    isResultNotEmpty = resultSet.isBeforeFirst();
                }
            }
        }
        catch (SQLException | CanNotConnectException e ) {
            e.printStackTrace();
        }
        return isResultNotEmpty;
    }

    private Optional<Connection> getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    static class CanNotConnectException extends Exception {
        public CanNotConnectException(String message) {
            super(message);
        }
    }
}


