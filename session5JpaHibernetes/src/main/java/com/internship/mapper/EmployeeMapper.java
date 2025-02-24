package com.lhind.mapper;

import com.lhind.model.dto.EmployeeDTO;
import com.internship.model.dto.entity.Employee;

public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {

    private final EmployeeDetailsMapper employeeDetailsMapper;

    public EmployeeMapper() {
        this.employeeDetailsMapper = new EmployeeDetailsMapper();
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setUserName(employeeDTO.getUsername());
        employee.setEmployeeDetails(employeeDetailsMapper.toEntity(employeeDTO.getEmployeeDetailsDTO()));
        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setMiddleName(employee.getMiddleName());
        employeeDTO.setEmployeeDetailsDTO(employeeDetailsMapper.toDto(employee.getEmployeeDetails()));
        return employeeDTO;
    }
}