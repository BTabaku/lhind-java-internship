package com.internship.service.impl;

import com.internship.mapper.EmployeeMapper;
import com.internship.model.dto.EmployeeDTO;
import com.internship.repository.EmployeeRepository;
import com.internship.repository.impl.EmployeeRepositoryImpl;
import com.internship.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
        this.employeeMapper = new EmployeeMapper();
    }

    @Override
    public List<EmployeeDTO> loadAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void saveEmployee(EmployeeDTO employee) {
        employeeRepository.save(employeeMapper.toEntity(employee));
    }

    @Override
    public List<EmployeeDTO> findAllNamedQuery(String username) {
        return employeeRepository.findAllNamedQuery(username).stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto).orElse(null);
    }

}