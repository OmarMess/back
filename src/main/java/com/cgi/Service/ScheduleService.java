package com.cgi.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.model.Notif;
import com.cgi.model.Schedule;
import com.cgi.model.UserApp;
import com.cgi.repository.NotifRepository;
import com.cgi.repository.ScheduleRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final NotifRepository notifRepository;
    private final EmailService emailService;
    
    @Autowired
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
    
    
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, NotifRepository notifRepository, EmailService emailService) {
        this.scheduleRepository = scheduleRepository;
        this.notifRepository = notifRepository;
        this.emailService = emailService;
    }

    public Schedule saveSchedule(String nom, String prenom, String email, UserApp evaluator, Date date,String meetinglink) {
        Schedule schedule = new Schedule();
        schedule.setNom(nom);
        schedule.setPrenom(prenom);
        schedule.setEmail(email);
        schedule.setEvaluator(evaluator);
        schedule.setDate(date);
        schedule.setMeetinglink(meetinglink);
        Schedule saved = scheduleRepository.save(schedule);
        schedule.setEvaluated(false);

        Notif notif = new Notif();
        notif.setEvaluator(evaluator);
        notif.setMessage("You have an interview with " + nom + " " + prenom + " on " + date);
        notifRepository.save(notif);

        String evaluatorName = evaluator.getNom() + " " + evaluator.getPrenom();
        String candidateName = nom + " " + prenom;
        String subject = "Interview Scheduled";
        String interviewDate = date.toString();

        emailService.sendHtmlMessage(evaluator.getMail(), subject, evaluatorName, candidateName, interviewDate,meetinglink);

        return saved;
    }
     
    
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }
    
    public Schedule updateEvaluatedStatus(Long scheduleId, boolean evaluated) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setEvaluated(evaluated);
        return scheduleRepository.save(schedule);
    }
}


