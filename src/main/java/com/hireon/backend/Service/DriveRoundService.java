package com.hireon.backend.Service;

import com.hireon.backend.DTO.DriveRoundRequest;
import com.hireon.backend.Model.Drive;
import com.hireon.backend.Model.DriveRound;
import com.hireon.backend.Repository.DriveRepo;
import com.hireon.backend.Repository.DriveRoundRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DriveRoundService {

    @Autowired
    private DriveRoundRepo driveRoundRepo;

    @Autowired
    private DriveRepo driveRepo;



    public DriveRound createRound(
            Long driveId,
            DriveRoundRequest request) {

        Drive drive = driveRepo.findById(driveId)
                .orElseThrow(() ->
                        new RuntimeException("Drive not found"));

        DriveRound round = new DriveRound();

        round.setDrive(drive);
        round.setRoundName(request.getRoundName());
        round.setRoundLink(request.getRoundLink());
        round.setFinal(request.isFinal());
        round.setDescription(request.getDescription());
        round.setStartTime(request.getStartTime());
        round.setEndTime(request.getEndTime());

        return driveRoundRepo.save(round);
    }



    public List<DriveRound> getAllRounds(Long driveId) {

        List<DriveRound> all = driveRoundRepo.findAll();

        List<DriveRound> result = new ArrayList<>();

        for (DriveRound round : all) {

            if (round.getDrive().getDriveId().equals(driveId)) {
                result.add(round);
            }
        }

        result.sort(
                Comparator.comparing(
                        DriveRound::getStartTime,
                        Comparator.nullsLast(
                                Comparator.naturalOrder()
                        )
                )
        );

        return result;
    }



    public DriveRound getRoundById(Long roundId) {

        return driveRoundRepo.findById(roundId)
                .orElseThrow(() ->
                        new RuntimeException("Round not found"));
    }



    public DriveRound updateRound(
            Long roundId,
            DriveRoundRequest request) {

        DriveRound round = driveRoundRepo.findById(roundId)
                .orElseThrow(() ->
                        new RuntimeException("Round not found"));

        round.setRoundName(request.getRoundName());
        round.setRoundLink(request.getRoundLink());
        round.setFinal(request.isFinal());
        round.setDescription(request.getDescription());
        round.setStartTime(request.getStartTime());
        round.setEndTime(request.getEndTime());

        return driveRoundRepo.save(round);
    }



    public void deleteRound(Long roundId) {

        DriveRound round = driveRoundRepo.findById(roundId)
                .orElseThrow(() ->
                        new RuntimeException("Round not found"));

        driveRoundRepo.delete(round);
    }
}