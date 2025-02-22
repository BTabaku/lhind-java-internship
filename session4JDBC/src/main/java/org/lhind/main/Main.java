package org.lhind.main;

import com.internship.session4jdbc.util.JdbcConnection;
import com.internship.session4jdbc.model.Employee;
import com.internship.session4jdbc.repository.EmployeeRepository;


import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        System.out.println("App Started!");

//        try (Connection connection = JdbcConnection.connect()) {
//            if (connection != null && !connection.isClosed()) {
//                System.out.println("Connection successful!");
//            } else {
//                System.out.println("Failed to establish connection.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        EmployeeRepository employeeRepository = new EmployeeRepository();

        // Test exists method
        boolean exists = employeeRepository.exists(1002);
        System.out.println("Employee exists: " + exists);

        // Test findById method
        employeeRepository.findById(1).ifPresent(employee -> System.out.println("Employee found: " + employee));

        // Test findAll method
        employeeRepository.findAll().forEach(employee -> System.out.println("Employee: " + employee));

        // Test delete method
// Test delete method
        employeeRepository.findById(1002).ifPresent(employee -> {
            System.out.println("Deleting employee: " + employee);
            employeeRepository.delete(1002);
            System.out.println("Employee deleted");
        });

    }
}
