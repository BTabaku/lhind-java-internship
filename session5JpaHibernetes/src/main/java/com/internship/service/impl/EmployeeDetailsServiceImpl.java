package com.internship.service.impl;

import com.internship.model.entity.EmployeeDetails;
import com.internship.repository.EmployeeDetailsRepository;
import com.internship.repository.impl.EmployeeDetailsRepositoryImpl;
import com.internship.service.EmployeeDetailsService;

public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeDetailsServiceImpl() {
        this.employeeDetailsRepository = new EmployeeDetailsRepositoryImpl();
    }

    @Override
    public void saveEmployeeDetails(EmployeeDetails employeeDetails) {
        employeeDetailsRepository.save(employeeDetails);
    }
}
