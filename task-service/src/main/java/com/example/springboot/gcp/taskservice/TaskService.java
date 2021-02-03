package com.example.springboot.gcp.taskservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskService {
    
    private static Map<String,Task> tasks = new HashMap<>();

    @PostMapping("/task")
    public String addTask(@RequestBody Task task){
        tasks.put(task.getId(),task);
        return "task added successfully";
    }

    @PutMapping("/task")
    public String updateTask(@RequestBody Task task){
        tasks.put(task.getId(),task);
        return "task updated successfully";
    }

    @GetMapping("/task/{id}")
    public Task getTaskDetails(@PathVariable  String id){
        return tasks.get(id);
    }


    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable String id) {
        tasks.remove(id);
        return "task deleted successfully";
    }

    @GetMapping("/task")
    public List<Task> getTaskList(){
        return new ArrayList<Task>(tasks.values());
    }

    
}