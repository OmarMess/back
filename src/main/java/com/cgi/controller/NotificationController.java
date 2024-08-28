package com.cgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cgi.model.Notif;
import com.cgi.model.UserApp;
import com.cgi.repository.NotifRepository;
import com.cgi.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
    @Autowired
    private NotifRepository notificationRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{evaluatorId}")
    public ResponseEntity<?> getNotifications(@PathVariable Long evaluatorId) {
        UserApp evaluator = userService.findById(evaluatorId);
        if (evaluator == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid evaluator ID");
        }
        
        List<Notif> notifications = notificationRepository.findByEvaluator(evaluator);
        return ResponseEntity.ok(notifications);
    }
}
