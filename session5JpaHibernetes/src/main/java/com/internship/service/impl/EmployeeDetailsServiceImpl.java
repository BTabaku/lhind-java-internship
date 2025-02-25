package com.lhind.service.impl;

import com.lhind.model.entity.EmployeeDetails;
import com.lhind.repository.EmployeeDetailsRepository;
import com.lhind.repository.impl.EmployeeDetailsRepositoryImpl;
import com.lhind.service.EmployeeDetailsService;

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
