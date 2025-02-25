package com.lhind.main;

import com.lhind.model.dto.EmployeeDTO;
import com.lhind.model.dto.EmployeeDetailsDTO;
import com.lhind.model.entity.Booking;
import com.lhind.model.entity.Employee;
import com.lhind.model.entity.EmployeeDetails;
import com.lhind.model.enums.BookingStatus;
import com.lhind.model.enums.EmploymentStatus;
import com.lhind.repository.BookingRepository;
import com.lhind.repository.EmployeeRepository;
import com.lhind.repository.impl.BookingRepositoryImpl;
import com.lhind.repository.impl.EmployeeRepositoryImpl;
import com.lhind.service.EmployeeDetailsService;
import com.lhind.service.EmployeeService;
import com.lhind.service.impl.EmployeeDetailsServiceImpl;
import com.lhind.service.impl.EmployeeServiceImpl;


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
