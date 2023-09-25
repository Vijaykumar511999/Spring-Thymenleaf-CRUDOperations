package com.springthymenleaf.Springbootthymenleaf.Controller;

import com.springthymenleaf.Springbootthymenleaf.Entity.Employee;
import com.springthymenleaf.Springbootthymenleaf.Repository.EmployeeRepository;
import com.springthymenleaf.Springbootthymenleaf.utility.LoggerUtil;
import com.springthymenleaf.Springbootthymenleaf.utility.LoggerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeController {



    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping({"/listemployee","/","/list"})
    public ModelAndView showEmployee(){
        LoggerUtility.LoggerforSpringThymenleaf.debug("show employee method is started..");
        ModelAndView modelAndView = new ModelAndView("list-employees");
        LoggerUtility.LoggerforSpringThymenleaf.debug("list-employees html also called..");
        List<Employee> lst= employeeRepository.findAll();
        LoggerUtility.LoggerforSpringThymenleaf.debug("data fetched successfully");
        modelAndView.addObject("employees",lst);
       return modelAndView;
    }
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        mav.addObject("employee", newEmployee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int employeeId) {
        LoggerUtility.LoggerforSpringThymenleaf.debug("showUpdateForm method is called..");
        ModelAndView mav = new ModelAndView("add-employee-form");
        LoggerUtility.LoggerforSpringThymenleaf.debug("add-employee form HTML call also happen..");
        Employee employee = employeeRepository.findById(employeeId).get();
        LoggerUtility.LoggerforSpringThymenleaf.debug("Employee details fetched successfully..");
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }
}
