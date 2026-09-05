package com.hireon.backend.Controller;

import com.hireon.backend.Enum.ShortlistStatus;
import com.hireon.backend.Model.ShortlistedStudent;
import com.hireon.backend.Service.ShortlistedStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ShortlistedStudentController {

    @Autowired
    private ShortlistedStudentService service;

    @PostMapping("/rounds/{roundId}/shortlist")
    public ShortlistedStudent shortlist(
            @PathVariable Long roundId,
            @RequestParam Long studentId) {

        return service.shortlistStudent(roundId, studentId);
    }

    @GetMapping("/rounds/{roundId}/shortlisted")
    public List<ShortlistedStudent> getByRound(
            @PathVariable Long roundId) {

        return service.getShortlistedByRound(roundId);
    }

    @GetMapping("/students/{sId}/shortlisted")
    public List<ShortlistedStudent> getByStudent(
            @PathVariable Long sId) {

        return service.getStudentShortlisted(sId);
    }

    @PatchMapping("/shortlisted/{shortlistId}/status")
    public ShortlistedStudent updateStatus(
            @PathVariable Long shortlistId,
            @RequestParam ShortlistStatus status) {

        return service.updateStatus(shortlistId, status);
    }

    @PostMapping("/shortlisted/{shortlistId}/feedback")
    public ShortlistedStudent addFeedback(
            @PathVariable Long shortlistId,
            @RequestParam String feedbackUrl) {

        return service.addFeedback(shortlistId, feedbackUrl);
    }

    @GetMapping("/shortlisted/{shortlistId}/feedback")
    public String getFeedback(
            @PathVariable Long shortlistId) {

        return service.getFeedback(shortlistId);
    }

    @GetMapping("/students/{sId}/feedback")
    public List<ShortlistedStudent> getFeedbackByStudent(
            @PathVariable Long sId) {

        return service.getStudentShortlisted(sId);
    }

    @DeleteMapping("/shortlisted/{shortlistId}")
    public String delete(
            @PathVariable Long shortlistId) {

        service.deleteShortlist(shortlistId);

        return "Shortlist deleted successfully";
    }
}
