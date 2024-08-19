package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @GetMapping("/books")
    public ResponseEntity<String> redirectToBooks() {
        return new ResponseEntity<>(
            "Navigate to /library/books for Books API", HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<String> redirectToStudents() {
        return new ResponseEntity<>(
            "Navigate to /library/students for Students API", HttpStatus.OK);
    }


    @GetMapping("/tasks")
    public ResponseEntity<String> redirectToTasks() {
        return new ResponseEntity<>(
            "Navigate to /library/tasks for Tasks API", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<String> redirectToProducts() {
        return new ResponseEntity<>(
            "Navigate to /library/products for Products API", HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<String> redirectToEmployees() {
        return new ResponseEntity<>(
            "Navigate to /library/employees for Employees API", HttpStatus.OK);
    }
}
