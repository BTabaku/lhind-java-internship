package com.internship.main;

import com.internship.model.dto.EmployeeDTO;
import com.internship.model.dto.EmployeeDetailsDTO;
import com.internship.model.enums.EmploymentStatus;
import com.internship.service.EmployeeService;
import com.internship.service.impl.EmployeeServiceImpl;


import java.util.Date;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        EmployeeDTO employee = new EmployeeDTO();
        employee.setEmploymentStatus(EmploymentStatus.ACTIVE);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setUsername("john95");
        employee.setUsername("antowqewew");
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setEmail("testmail@gmail.com");
        employeeDetailsDTO.setEmploymentDate(new Date());
        employee.setEmployeeDetailsDTO(employeeDetailsDTO);
        employeeService.saveEmployee(employee);
        List<EmployeeDTO> list = employeeService.findAllNamedQuery("John");
        System.out.println(list);

        // Use the findById method
        EmployeeDTO employeeDTO = employeeService.findById(1L);
        System.out.println(employeeDTO);
    }

//    public static void main(String[] args) {
//        EmployeeDetailsService employeeDetailsService = new EmployeeDetailsServiceImpl();
//        Employee employee = new Employee();
//        employee.setId(1L);
//        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setEmail("ani@gmail.com");
//        employeeDetails.setPhoneNumber("123456789");
//        employeeDetails.setEmploymentDate(new Date());
//        employeeDetails.setEmployee(employee);
//        employeeDetails.setEmploymentStatus(EmploymentStatus.ACTIVE);
//        employeeDetailsService.saveEmployeeDetails(employeeDetails);
//      //  List<EmployeeDetails> list = employeeDetailsService.findAllNamedQuery("Doe");
//        //  System.out.println(list);
//
//    }


//    public static void main(String[] args) {
//
//        BookingRepository bookingRepository = new BookingRepositoryImpl();
//     List<Booking> bookings = bookingRepository.findByEmployeeLastName("Doe");
//        // List<EmployeeDetails> bookingsTest = bookingRepository.findFromNativeQuery("1234");
//       // System.out.println(bookings);
//    }

}
