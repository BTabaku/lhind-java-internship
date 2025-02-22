package com.internship.session4jdbc.repository;

import com.internship.session4jdbc.mapper.EmployeeMapper;
import com.internship.session4jdbc.model.Employee;
import com.internship.session4jdbc.model.enums.EmployeeQuery;
import com.internship.session4jdbc.util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Repository<Employee, Integer> {

    private static final String SELECT_ALL = "SELECT * FROM employees;";
    private static final String SELECT_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    private static final String CHECK_IF_EXISTS = "SELECT 1 FROM employees WHERE employeeNumber = ?;";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?;";

    // Employee Deletion Queries
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employeeNumber = ?;";
    private static final String UPDATE_REFERENCING_ROWS = "UPDATE employees SET reportsTo = NULL WHERE reportsTo = ?;";

    private EmployeeMapper employeeMapper = EmployeeMapper.getInstance();

    @Override
    public List<Employee> findAll() {
        final List<Employee> response = new ArrayList<>();
        try (final Connection connection = JdbcConnection.connect(); final PreparedStatement statement = connection.prepareStatement(EmployeeQuery.SELECT_ALL.getQuery())) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                response.add(employeeMapper.toEntity(result));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    @Override
    public Optional<Employee> findById(final Integer id) {
        try (final Connection connection = JdbcConnection.connect(); final PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                final Employee employee = employeeMapper.toEntity(result);
                return Optional.of(employee);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean exists(final Integer integer) {
        // TODO: Implement a method which checks if an employee with the given id exists in the employees table

        try (final Connection connection = JdbcConnection.connect(); final PreparedStatement statement = connection.prepareStatement(CHECK_IF_EXISTS)) {
            statement.setInt(1, integer);
            final ResultSet result = statement.executeQuery();
            return result.next();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Employee save(final Employee employee) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         *
         */
        try (final Connection connection = JdbcConnection.connect()) {
            if (exists(employee.getEmployeeNumber())) {
                try (final PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
                    statement.setString(1, employee.getLastName());
                    statement.setString(2, employee.getFirstName());
                    statement.setString(3, employee.getExtension());
                    statement.setString(4, employee.getEmail());
                    statement.setString(5, employee.getOfficeCode());
                    statement.setInt(6, employee.getReportsTo());
                    statement.setString(7, employee.getJobTitle());
                    statement.setInt(8, employee.getEmployeeNumber());
                    statement.executeUpdate();
                }
            } else {
                try (final PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE)) {
                    statement.setInt(1, employee.getEmployeeNumber());
                    statement.setString(2, employee.getLastName());
                    statement.setString(3, employee.getFirstName());
                    statement.setString(4, employee.getExtension());
                    statement.setString(5, employee.getEmail());
                    statement.setString(6, employee.getOfficeCode());
                    statement.setInt(7, employee.getReportsTo());
                    statement.setString(8, employee.getJobTitle());
                    statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(final Integer integer) {
        /*
         * TODO: Implement a method which deletes an employee given the id
         *
         */
        try (final Connection connection = JdbcConnection.connect()) {
            // Update referencing rows in case it has dependencies and cannot be deleted
            try (final PreparedStatement updateStatement = connection.prepareStatement(UPDATE_REFERENCING_ROWS)) {
                updateStatement.setInt(1, integer);
                updateStatement.executeUpdate();
            }

            // Delete the employee
            try (final PreparedStatement deleteStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
                deleteStatement.setInt(1, integer);
                deleteStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
