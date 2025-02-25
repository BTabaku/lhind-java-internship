package com.lhind.service;

import com.lhind.model.dto.EmployeeDTO;

import java.util.List;


public interface EmployeeService {

    List<EmployeeDTO> loadAllEmployees();

    void saveEmployee(EmployeeDTO employee);

    List<EmployeeDTO> findAllNamedQuery(String username);

}
