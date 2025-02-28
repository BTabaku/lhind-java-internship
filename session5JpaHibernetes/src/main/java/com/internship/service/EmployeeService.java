package com.internship.serv`ice;

import com.internship.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> loadAllEmployees();

    void saveEmployee(EmployeeDTO employee);
    List<EmployeeDTO> findAllNamedQuery(String name);
    EmployeeDTO findById(Long id); // Add this method
}
