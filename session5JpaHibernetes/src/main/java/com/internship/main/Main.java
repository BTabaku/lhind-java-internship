package com.internship.main;

import com.internship.model.dto.EmployeeDTO;
import com.internship.model.dto.EmployeeDetailsDTO;
import com.internship.model.entity.Booking;
import com.internship.model.entity.Employee;
import com.internship.model.entity.EmployeeDetails;
import com.internship.model.enums.BookingStatus;
import com.internship.model.enums.EmploymentStatus;
import com.internship.repository.BookingRepository;
import com.internship.repository.EmployeeRepository;
import com.internship.repository.impl.BookingRepositoryImpl;
import com.internship.repository.impl.EmployeeRepositoryImpl;
import com.internship.service.EmployeeDetailsService;
import com.internship.service.EmployeeService;
import com.internship.service.impl.EmployeeDetailsServiceImpl;
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
        employeeDetailsDTO.setEmail("ani@gmail.com");
        employeeDetailsDTO.setEmploymentDate(new Date());
        employee.setEmployeeDetailsDTO(employeeDetailsDTO);
        employeeService.saveEmployee(employee);
        List<EmployeeDTO> list = employeeService.findAllNamedQuery("John");
        // todo EmployeeDTO employeeDTO = employeeService.findById(Long id);+
        System.out.println(list);

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
