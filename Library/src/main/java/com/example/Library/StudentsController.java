package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/students")
public class StudentsController {
    private List<Students> students = new ArrayList<>();
    private Long idCounter = 1L;


    @GetMapping
    public ResponseEntity<List<Students>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addStudents(@RequestBody Students student) {
        if (student == null) {
            return new ResponseEntity<>("No Student provided", HttpStatus.BAD_REQUEST);
        }
        student.setId(idCounter++);
        students.add(student);
        return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudents(@PathVariable Long id, @RequestBody Students updatedStudents) {
        Optional<Students> studentOptional = students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
        
        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            student.setName(updatedStudents.getName());
            student.setSurname(updatedStudents.getSurname());
            student.setYear(updatedStudents.getYear());
            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudents(@PathVariable Long id) {
        Iterator<Students> iterator = students.iterator();

        while(iterator.hasNext()) {
            Students student = iterator.next();
            if(student.getId().equals(id)) {
                iterator.remove();
                return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }
}
