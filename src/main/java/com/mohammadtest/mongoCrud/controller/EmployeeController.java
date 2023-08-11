package com.mohammadtest.mongoCrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mohammadtest.mongoCrud.model.Employee;
import com.mohammadtest.mongoCrud.service.EmployeeService;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveOrUpdate(@RequestBody Employee emp) {
        try {
            Employee employee = employeeService.saveOrUpdate(emp);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<List<Employee>>(employeeService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
        Optional<Employee> optional = employeeService.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateOne(@PathVariable("id") String id, @RequestBody Employee employee) {
        Optional<Employee> optional = employeeService.findById(id);

        if (optional.isPresent()) {
            Employee _employee = optional.get();
            _employee.setFname(employee.getFname());
            _employee.setLname(employee.getLname());
            _employee.setEmail(employee.getEmail());
            _employee.setPhone(employee.getPhone());
            _employee.setBirthdate(employee.getBirthdate());
            return new ResponseEntity<>(employeeService.saveOrUpdate(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @DeleteMapping("/employees")
    // ResponseEntity<String> delete(@RequestBody Employee emp) {
    // return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    // }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            employeeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") String id) {
        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}