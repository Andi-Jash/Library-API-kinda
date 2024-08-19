package com.example.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        if (task == null) {
            return new ResponseEntity<>("No Task provided", HttpStatus.BAD_REQUEST);
        }
        task.setId(idCounter++);
        tasks.add(task);
        return new ResponseEntity<>("Task added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Optional<Task> taskOptional = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId().equals(id) && task.getCompleted()) {
                iterator.remove();
                return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Task not found or not completed", HttpStatus.NOT_FOUND);
    }
}
