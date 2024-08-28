package com.cgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cgi.dtos.ScheduleRequestDTO;
import com.cgi.model.Schedule;
import com.cgi.model.UserApp;
import com.cgi.services.UserService;
import com.cgi.Service.ScheduleService;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, UserService userService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDTO request) {
        try {
            UserApp evaluator = userService.findById(request.getEvaluatorId());
            if (evaluator == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid evaluator ID");
            }
            Schedule schedule = scheduleService.saveSchedule(
                    request.getNom(), 
                    request.getPrenom(),
                    request.getEmail(), 
                    evaluator, 
                    request.getDate(),
                    request.getMeetinglink()
                    
            );
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to schedule: " + e.getMessage());
        }
    }
    
    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
    
    
    @GetMapping("/evaluator/{id}")
    public ResponseEntity<List<Schedule>> getSchedulesByEvaluator(@PathVariable Long id) {
        List<Schedule> schedules = userService.getSchedulesByEvaluatorId(id);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Long id) {
        return scheduleService.findById(id); 
    }
    
    
}
