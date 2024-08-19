package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        if (employee == null) {
            return new ResponseEntity<>("No Employee provided", HttpStatus.BAD_REQUEST);
        }
        employee.setId(idCounter++);
        employees.add(employee);
        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(updatedEmployee.getName());
            employee.setPosition(updatedEmployee.getPosition());
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId().equals(id)) {
                iterator.remove();
                return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
