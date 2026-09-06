
package com.hireon.backend.Service;

import com.hireon.backend.DTO.AdvanceRoundRequest;
import com.hireon.backend.Enum.ShortlistStatus;
import com.hireon.backend.Model.DriveRound;
import com.hireon.backend.Model.ShortlistedStudent;
import com.hireon.backend.Model.Student;
import com.hireon.backend.Repository.DriveRoundRepo;
import com.hireon.backend.Repository.ShortlistedStudentRepo;
import com.hireon.backend.Repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortlistedStudentService {

    @Autowired
    private ShortlistedStudentRepo shortlistedStudentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private DriveRoundRepo driveRoundRepo;

    public ShortlistedStudent shortlistStudent(Long roundId, Long studentId) {

        DriveRound round = driveRoundRepo.findById(roundId)
                .orElseThrow(() ->
                        new RuntimeException("Round not found"));

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        ShortlistedStudent shortlistedStudent =
                new ShortlistedStudent();

        shortlistedStudent.setRound(round);
        shortlistedStudent.setStudent(student);
        shortlistedStudent.setStatus(ShortlistStatus.PENDING);

        return shortlistedStudentRepo.save(shortlistedStudent);
    }


    public List<ShortlistedStudent> getShortlistedByRound(Long roundId) {

        return shortlistedStudentRepo.findByRound_RoundId(roundId);
    }



    public List<ShortlistedStudent> getShortlistedByStudent(Long studentId) {


        return shortlistedStudentRepo.findByStudent_SId(studentId);
    }



    public ShortlistedStudent updateStatus(
            Long shortlistId,
            ShortlistStatus  status) {

        ShortlistedStudent shortlistedStudent =
                shortlistedStudentRepo.findById(shortlistId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shortlist entry not found"));

        shortlistedStudent.setStatus(status);

        return shortlistedStudentRepo.save(shortlistedStudent);
    }



    public ShortlistedStudent addFeedback(
            Long shortlistId,
            String feedbackUrl) {

        ShortlistedStudent shortlistedStudent =
                shortlistedStudentRepo.findById(shortlistId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shortlist entry not found"));

        shortlistedStudent.setFeedbackUrl(feedbackUrl);

        return shortlistedStudentRepo.save(shortlistedStudent);
    }


    public String getFeedback(Long shortlistId) {

        ShortlistedStudent shortlistedStudent =
                shortlistedStudentRepo.findById(shortlistId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shortlist entry not found"));

        return shortlistedStudent.getFeedbackUrl();
    }

    public List<ShortlistedStudent> getStudentFeedback(Long studentId) {

        return shortlistedStudentRepo.findByStudent_SId(studentId);
    }



    public void deleteShortlist(Long shortlistId) {

        ShortlistedStudent shortlistedStudent =
                shortlistedStudentRepo.findById(shortlistId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shortlist entry not found"));

        shortlistedStudentRepo.delete(shortlistedStudent);
    }

    public List<ShortlistedStudent> advanceToNextRound(
            Long fromRoundId,
            AdvanceRoundRequest request) {

        DriveRound toRound = driveRoundRepo.findById(request.getToRoundId())
                .orElseThrow(() -> new RuntimeException("Target round not found"));

        // get everyone in the current round
        List<ShortlistedStudent> allInRound =
                shortlistedStudentRepo.findByRound_RoundId(fromRoundId);

        // get only the ones whose reg_no is in the punch list
        List<ShortlistedStudent> advancing =
                shortlistedStudentRepo.findByRound_RoundIdAndStudent_Reg_noIn(
                        fromRoundId, request.getRegNos());

        // mark advancing students as SELECTED in current round
        for (ShortlistedStudent entry : advancing) {
            entry.setStatus(ShortlistStatus.SELECTED);
        }
        shortlistedStudentRepo.saveAll(advancing);

        // mark the rest as REJECTED in current round
        List<Long> advancingIds = new ArrayList<>();
        for (ShortlistedStudent entry : advancing) {
            advancingIds.add(entry.getShortlistId());
        }

        for (ShortlistedStudent entry : allInRound) {
            if (!advancingIds.contains(entry.getShortlistId())) {
                entry.setStatus(ShortlistStatus.REJECTED);
            }
        }
        shortlistedStudentRepo.saveAll(allInRound);

        // create new PENDING entries in the next round for advancing students
        List<ShortlistedStudent> newEntries = new ArrayList<>();
        for (ShortlistedStudent entry : advancing) {
            ShortlistedStudent next = new ShortlistedStudent();
            next.setRound(toRound);
            next.setStudent(entry.getStudent());
            next.setStatus(ShortlistStatus.PENDING);
            newEntries.add(next);
        }

        return shortlistedStudentRepo.saveAll(newEntries);
    }

}