package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.exeption_handling.EmployeeIncorrectData;
import com.zaurtregulov.spring.rest.exeption_handling.NoSuchEmployeeException;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //это контроллер приложения который управляет Rest запросами и возвращает ответы в формате JSON
@RequestMapping("/api") //аннотация, которая указывает, что URL-адрес начинается с "/api"
public class MyController {

    @Autowired //аннотация @Autowired используется для автоматического создания бинов
    private EmployeeService employeeService;

    @GetMapping("/employees")
    //аннотация, которая указывает, что URL-адрес "/employees" будет обрабатываться этим контроллером методом GET
    public List<Employee> showAllEmployees() {

        List<Employee> allEmployees = employeeService.getAllEmployees();//получаем список всех сотрудников

        return allEmployees;
    }
    @GetMapping("/employee/id/{id}")//аннотация, которая указывает, что URL-адрес "/employees/{id}" будет обрабатываться этим контроллером методом GET
    public Employee getEmployee(@PathVariable("id") int id) {

        Employee employee = employeeService.getEmployee(id);//получаем сотрудника по id

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in database");
        }

        return employee;
    }
    @GetMapping("/employee/name/{name}") // аннотация, которая указывает, что URL-адрес "/employees/{name}" будет обрабатываться этим контроллером методом GET
    public Employee getEmployeeByName(@PathVariable("name") String name) {

        Employee employee = employeeService.getEmployee(name); // получаем сотрудника по имени

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with name = " + name + " in database");
        }

        return employee;
    }
    // только через postman
    @PostMapping("/employee/post")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employee/put")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;
    }
}
