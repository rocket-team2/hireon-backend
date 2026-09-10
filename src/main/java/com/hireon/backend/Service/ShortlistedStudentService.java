package com.hireon.backend.Service;

import com.hireon.backend.DTO.AdvanceRoundRequest;
import com.hireon.backend.Enum.ShortlistStatus;
import com.hireon.backend.Model.DriveRegistration;
import com.hireon.backend.Model.DriveRound;
import com.hireon.backend.Model.ShortlistedStudent;
import com.hireon.backend.Model.Student;
import com.hireon.backend.Repository.DriveRegistrationRepo;
import com.hireon.backend.Repository.DriveRoundRepo;
import com.hireon.backend.Repository.ShortlistedStudentRepo;
import com.hireon.backend.Repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShortlistedStudentService {

    @Autowired
    private ShortlistedStudentRepo shortlistedStudentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private DriveRoundRepo driveRoundRepo;

    @Autowired
    private DriveRoundService driveRoundService;

    public ShortlistedStudent shortlistStudent(Long roundId, Long studentId) {
    private DriveRegistrationRepo driveRegistrationRepo;

    @Autowired
    private DriveRoundService driveRoundService;

    public void autoInitializeRound1(Long roundId) {
        DriveRound currentRound = driveRoundRepo.findById(roundId).orElse(null);
        if (currentRound == null || currentRound.getDrive() == null) return;

        Long driveId = currentRound.getDrive().getDriveId();
        List<DriveRound> allRounds = driveRoundService.getAllRounds(driveId);
        if (allRounds.isEmpty() || !allRounds.get(0).getRoundId().equals(roundId)) {
            return; // Not round 1
        }

        List<DriveRegistration> registrations = driveRegistrationRepo.findByDriveId(driveId);
        List<ShortlistedStudent> existingInRound1 = shortlistedStudentRepo.findByRound_RoundId(roundId);

        Set<Long> existingStudentIds = existingInRound1.stream()
                .filter(s -> s.getStudent() != null)
                .map(s -> s.getStudent().getSId())
                .collect(Collectors.toSet());

        List<ShortlistedStudent> toAdd = new ArrayList<>();
        for (DriveRegistration reg : registrations) {
            if (reg.getStudent() != null && !existingStudentIds.contains(reg.getStudent().getSId())) {
                ShortlistedStudent entry = new ShortlistedStudent();
                entry.setRound(currentRound);
                entry.setStudent(reg.getStudent());
                entry.setStatus(ShortlistStatus.PENDING);
                toAdd.add(entry);
            }
        }
        if (!toAdd.isEmpty()) {
            shortlistedStudentRepo.saveAll(toAdd);
        }
    }

    public ShortlistedStudent shortlistStudent(Long roundId, Long studentId) {
        DriveRound round = driveRoundRepo.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found"));

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Optional<ShortlistedStudent> existingOpt = shortlistedStudentRepo.findByRound_RoundIdAndStudent_sId(roundId, studentId);
        if (existingOpt.isPresent()) {
            return existingOpt.get();
        }

        ShortlistedStudent shortlistedStudent = new ShortlistedStudent();
        shortlistedStudent.setRound(round);
        shortlistedStudent.setStudent(student);
        shortlistedStudent.setStatus(ShortlistStatus.PENDING);

        return shortlistedStudentRepo.save(shortlistedStudent);
    }


    @Transactional
    public List<ShortlistedStudent> getShortlistedByRound(Long roundId) {

        DriveRound currentRound = driveRoundRepo.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found"));

        if (currentRound.getDrive() != null) {
            Long driveId = currentRound.getDrive().getDriveId();
            List<DriveRound> rounds = driveRoundService.getAllRounds(driveId);

            int currentIndex = -1;
            for (int i = 0; i < rounds.size(); i++) {
                if (rounds.get(i).getRoundId().equals(roundId)) {
                    currentIndex = i;
                    break;
                }
            }

            // If this is round 2 or later, ensure candidates SELECTED in previous round are auto-synced here
            if (currentIndex > 0) {
                DriveRound prevRound = rounds.get(currentIndex - 1);
                List<ShortlistedStudent> prevList =
                        shortlistedStudentRepo.findByRound_RoundId(prevRound.getRoundId());

                Set<Long> selectedStudentIdsInPrev = prevList.stream()
                        .filter(s -> s.getStatus() == ShortlistStatus.SELECTED && s.getStudent() != null)
                        .map(s -> s.getStudent().getSId())
                        .collect(Collectors.toSet());

                // Auto-add any missing selected students from previous round
                for (ShortlistedStudent prevEntry : prevList) {
                    if (prevEntry.getStatus() == ShortlistStatus.SELECTED && prevEntry.getStudent() != null) {
                        Long studentId = prevEntry.getStudent().getSId();
                        Optional<ShortlistedStudent> existing =
                                shortlistedStudentRepo.findByRound_RoundIdAndStudent_sId(roundId, studentId);
                        if (existing.isEmpty()) {
                            ShortlistedStudent autoEntry = new ShortlistedStudent();
                            autoEntry.setRound(currentRound);
                            autoEntry.setStudent(prevEntry.getStudent());
                            autoEntry.setStatus(ShortlistStatus.PENDING);
                            shortlistedStudentRepo.save(autoEntry);
                        }
                    }
                }

                // If a student in this round was NOT selected in the previous round, remove them from this round
                List<ShortlistedStudent> currentEntries = shortlistedStudentRepo.findByRound_RoundId(roundId);
                for (ShortlistedStudent entry : currentEntries) {
                    if (entry.getStudent() != null &&
                            !selectedStudentIdsInPrev.contains(entry.getStudent().getSId())) {
                        shortlistedStudentRepo.delete(entry);
                    }
                }
            }
        }

    public List<ShortlistedStudent> getShortlistedByRound(Long roundId) {
        autoInitializeRound1(roundId);
        return shortlistedStudentRepo.findByRound_RoundId(roundId);
    }

    public List<ShortlistedStudent> getShortlistedByStudent(Long studentId) {
        return shortlistedStudentRepo.findByStudent_sId(studentId);
    }

    public ShortlistedStudent updateStatus(Long shortlistId, ShortlistStatus status) {
        ShortlistedStudent shortlistedStudent = shortlistedStudentRepo.findById(shortlistId)
                .orElseThrow(() -> new RuntimeException("Shortlist entry not found"));

        shortlistedStudent.setStatus(status);
        ShortlistedStudent saved = shortlistedStudentRepo.save(shortlistedStudent);

        DriveRound currentRound = saved.getRound();
        if (currentRound != null && currentRound.getDrive() != null) {
            Long driveId = currentRound.getDrive().getDriveId();
            List<DriveRound> allRounds = driveRoundService.getAllRounds(driveId);
            int currentIndex = -1;
            for (int i = 0; i < allRounds.size(); i++) {
                if (allRounds.get(i).getRoundId().equals(currentRound.getRoundId())) {
                    currentIndex = i;
                    break;
                }
            }

    @Transactional
    public ShortlistedStudent updateStatus(
            Long shortlistId,
            ShortlistStatus status) {
            if (currentIndex >= 0 && currentIndex < allRounds.size() - 1) {
                DriveRound nextRound = allRounds.get(currentIndex + 1);
                Optional<ShortlistedStudent> nextEntryOpt = shortlistedStudentRepo
                        .findByRound_RoundIdAndStudent_sId(nextRound.getRoundId(), saved.getStudent().getSId());

                if (status == ShortlistStatus.SELECTED) {
                    if (nextEntryOpt.isEmpty()) {
                        ShortlistedStudent nextEntry = new ShortlistedStudent();
                        nextEntry.setRound(nextRound);
                        nextEntry.setStudent(saved.getStudent());
                        nextEntry.setStatus(ShortlistStatus.PENDING);
                        shortlistedStudentRepo.save(nextEntry);
                    } else if (nextEntryOpt.get().getStatus() == ShortlistStatus.REJECTED) {
                        nextEntryOpt.get().setStatus(ShortlistStatus.PENDING);
                        shortlistedStudentRepo.save(nextEntryOpt.get());
                    }
                } else if (status == ShortlistStatus.REJECTED || status == ShortlistStatus.PENDING) {
                    if (nextEntryOpt.isPresent() && nextEntryOpt.get().getStatus() == ShortlistStatus.PENDING) {
                        shortlistedStudentRepo.delete(nextEntryOpt.get());
                    }
                }
            }
        }

        return saved;
    }

        shortlistedStudent.setStatus(status);
        ShortlistedStudent saved = shortlistedStudentRepo.save(shortlistedStudent);

        DriveRound currentRound = shortlistedStudent.getRound();
        if (currentRound != null && currentRound.getDrive() != null) {
            Long driveId = currentRound.getDrive().getDriveId();
            List<DriveRound> rounds = driveRoundService.getAllRounds(driveId);

            int currentIndex = -1;
            for (int i = 0; i < rounds.size(); i++) {
                if (rounds.get(i).getRoundId().equals(currentRound.getRoundId())) {
                    currentIndex = i;
                    break;
                }
            }

            Student student = shortlistedStudent.getStudent();
            Long studentId = student != null ? student.getSId() : null;

            if (studentId != null) {
                if (status == ShortlistStatus.SELECTED) {
                    // If there is a next round, advance the student to the next round immediately with status PENDING
                    if (currentIndex >= 0 && currentIndex < rounds.size() - 1) {
                        DriveRound nextRound = rounds.get(currentIndex + 1);
                        Optional<ShortlistedStudent> existingInNext =
                                shortlistedStudentRepo.findByRound_RoundIdAndStudent_sId(nextRound.getRoundId(), studentId);

                        ShortlistedStudent nextEntry;
                        if (existingInNext.isPresent()) {
                            nextEntry = existingInNext.get();
                        } else {
                            nextEntry = new ShortlistedStudent();
                            nextEntry.setRound(nextRound);
                            nextEntry.setStudent(student);
                        }
                        // Always ensure entering the next round is marked PENDING
                        nextEntry.setStatus(ShortlistStatus.PENDING);
                        shortlistedStudentRepo.save(nextEntry);
                    } else if (currentIndex == rounds.size() - 1 || currentRound.isFinal()) {
                        // Final round cleared - update student placement status and company
                        if (currentRound.getDrive().getCompany() != null) {
                            student.setPlacement_status("Placed");
                            student.setCompany(currentRound.getDrive().getCompany());
                            studentRepo.save(student);
                        }
                    }
                } else if (status == ShortlistStatus.REJECTED || status == ShortlistStatus.PENDING) {
                    // If student is marked REJECTED or PENDING, remove from subsequent rounds
                    if (currentIndex >= 0) {
                        for (int i = currentIndex + 1; i < rounds.size(); i++) {
                            DriveRound subRound = rounds.get(i);
                            Optional<ShortlistedStudent> subEntry =
                                    shortlistedStudentRepo.findByRound_RoundIdAndStudent_sId(subRound.getRoundId(), studentId);
                            if (subEntry.isPresent()) {
                                shortlistedStudentRepo.delete(subEntry.get());
                            }
                        }
                    }
                    // If final round rejected/pending and was placed from this company, revert placement
                    if (currentIndex == rounds.size() - 1 || currentRound.isFinal()) {
                        if (student.getCompany() != null && currentRound.getDrive().getCompany() != null &&
                                student.getCompany().getComp_id().equals(currentRound.getDrive().getCompany().getComp_id())) {
                            student.setPlacement_status("Not Placed");
                            student.setCompany(null);
                            studentRepo.save(student);
                        }
                    }
                }
            }
        }

        return saved;
    }
    public List<ShortlistedStudent> processRoundExcel(Long roundId, List<String> regNos) {
        DriveRound currentRound = driveRoundRepo.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found"));

        Long driveId = currentRound.getDrive().getDriveId();

        autoInitializeRound1(roundId);

        List<DriveRound> allRounds = driveRoundService.getAllRounds(driveId);
        int currentIndex = -1;
        for (int i = 0; i < allRounds.size(); i++) {
            if (allRounds.get(i).getRoundId().equals(roundId)) {
                currentIndex = i;
                break;
            }
        }

        DriveRound nextRound = (currentIndex >= 0 && currentIndex < allRounds.size() - 1)
                ? allRounds.get(currentIndex + 1) : null;

        List<ShortlistedStudent> candidatesInRound = shortlistedStudentRepo.findByRound_RoundId(roundId);

        Set<String> targetRegNos = (regNos == null ? Collections.<String>emptyList() : regNos).stream()
                .filter(Objects::nonNull)
                .map(s -> s.trim().toUpperCase())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        List<ShortlistedStudent> toSaveCurrent = new ArrayList<>();
        List<ShortlistedStudent> toSaveNext = new ArrayList<>();
        List<ShortlistedStudent> toDeleteNext = new ArrayList<>();

        List<ShortlistedStudent> nextRoundEntries = (nextRound != null)
                ? shortlistedStudentRepo.findByRound_RoundId(nextRound.getRoundId())
                : Collections.emptyList();

        Map<Long, ShortlistedStudent> nextRoundStudentMap = nextRoundEntries.stream()
                .filter(e -> e.getStudent() != null)
                .collect(Collectors.toMap(e -> e.getStudent().getSId(), e -> e, (a, b) -> a));

        for (ShortlistedStudent entry : candidatesInRound) {
            Student student = entry.getStudent();
            if (student == null || student.getReg_no() == null) continue;

            String candidateRegNo = student.getReg_no().trim().toUpperCase();
            boolean isSelected = targetRegNos.contains(candidateRegNo);

            if (isSelected) {
                entry.setStatus(ShortlistStatus.SELECTED);
                toSaveCurrent.add(entry);

                if (nextRound != null) {
                    ShortlistedStudent existingNext = nextRoundStudentMap.get(student.getSId());
                    if (existingNext == null) {
                        ShortlistedStudent newNext = new ShortlistedStudent();
                        newNext.setRound(nextRound);
                        newNext.setStudent(student);
                        newNext.setStatus(ShortlistStatus.PENDING);
                        toSaveNext.add(newNext);
                    } else if (existingNext.getStatus() == ShortlistStatus.REJECTED) {
                        existingNext.setStatus(ShortlistStatus.PENDING);
                        toSaveNext.add(existingNext);
                    }
                }
            } else {
                entry.setStatus(ShortlistStatus.REJECTED);
                toSaveCurrent.add(entry);

                if (nextRound != null) {
                    ShortlistedStudent existingNext = nextRoundStudentMap.get(student.getSId());
                    if (existingNext != null && existingNext.getStatus() == ShortlistStatus.PENDING) {
                        toDeleteNext.add(existingNext);
                    }
                }
            }
        }

        shortlistedStudentRepo.saveAll(toSaveCurrent);
        if (!toSaveNext.isEmpty()) {
            shortlistedStudentRepo.saveAll(toSaveNext);
        }
        if (!toDeleteNext.isEmpty()) {
            shortlistedStudentRepo.deleteAll(toDeleteNext);
        }

        return shortlistedStudentRepo.findByRound_RoundId(roundId);
    }

    public ShortlistedStudent addFeedback(Long shortlistId, String feedbackUrl) {
        ShortlistedStudent shortlistedStudent = shortlistedStudentRepo.findById(shortlistId)
                .orElseThrow(() -> new RuntimeException("Shortlist entry not found"));

        shortlistedStudent.setFeedbackUrl(feedbackUrl);
        return shortlistedStudentRepo.save(shortlistedStudent);
    }

    public String getFeedback(Long shortlistId) {
        ShortlistedStudent shortlistedStudent = shortlistedStudentRepo.findById(shortlistId)
                .orElseThrow(() -> new RuntimeException("Shortlist entry not found"));

        return shortlistedStudent.getFeedbackUrl();
    }

    public List<ShortlistedStudent> getStudentFeedback(Long studentId) {
        return shortlistedStudentRepo.findByStudent_sId(studentId);
    }

    public void deleteShortlist(Long shortlistId) {
        ShortlistedStudent shortlistedStudent = shortlistedStudentRepo.findById(shortlistId)
                .orElseThrow(() -> new RuntimeException("Shortlist entry not found"));

        shortlistedStudentRepo.delete(shortlistedStudent);
    }

    public List<ShortlistedStudent> getStudentShortlisted(Long sId) {
        studentRepo.findById(sId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return shortlistedStudentRepo.findByStudent_sId(sId);
    }

    public List<ShortlistedStudent> advanceToNextRound(Long fromRoundId, AdvanceRoundRequest request) {
        if (request.getToRoundId() != null) {
            DriveRound toRound = driveRoundRepo.findById(request.getToRoundId())
                    .orElseThrow(() -> new RuntimeException("Target round not found"));

            List<ShortlistedStudent> allInRound = shortlistedStudentRepo.findByRound_RoundId(fromRoundId);
            List<ShortlistedStudent> advancing = shortlistedStudentRepo.findByRoundIdAndRegNos(fromRoundId, request.getRegNos());

            for (ShortlistedStudent entry : advancing) {
                entry.setStatus(ShortlistStatus.SELECTED);
            }
            shortlistedStudentRepo.saveAll(advancing);

            List<Long> advancingIds = advancing.stream().map(ShortlistedStudent::getShortlistId).collect(Collectors.toList());
            for (ShortlistedStudent entry : allInRound) {
                if (!advancingIds.contains(entry.getShortlistId())) {
                    entry.setStatus(ShortlistStatus.REJECTED);
                }
            }
            shortlistedStudentRepo.saveAll(allInRound);

            List<ShortlistedStudent> newEntries = new ArrayList<>();
            for (ShortlistedStudent entry : advancing) {
                ShortlistedStudent next = new ShortlistedStudent();
                next.setRound(toRound);
                next.setStudent(entry.getStudent());
                next.setStatus(ShortlistStatus.PENDING);
                newEntries.add(next);
            }
            return shortlistedStudentRepo.saveAll(newEntries);
        } else {
            return processRoundExcel(fromRoundId, request.getRegNos());
        }
    }
}