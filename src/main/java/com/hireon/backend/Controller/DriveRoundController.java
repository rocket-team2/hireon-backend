package com.hireon.backend.Controller;

import com.hireon.backend.DTO.DriveRoundRequest;
import com.hireon.backend.Model.DriveRound;
import com.hireon.backend.Service.DriveRoundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DriveRoundController {

    @Autowired
    private DriveRoundService driveRoundService;



    @PostMapping("/drives/{driveId}/rounds")
    public DriveRound createRound(
            @PathVariable Long driveId,
            @RequestBody DriveRoundRequest request) {

        return driveRoundService.createRound(
                driveId,
                request
        );
    }


    @GetMapping("/drives/{driveId}/rounds")
    public List<DriveRound> getAllRounds(
            @PathVariable Long driveId) {

        return driveRoundService.getAllRounds(driveId);
    }


    @GetMapping("/rounds/{roundId}")
    public DriveRound getRound(
            @PathVariable Long roundId) {

        return driveRoundService.getRoundById(roundId);
    }



    @PutMapping("/rounds/{roundId}")
    public DriveRound updateRound(
            @PathVariable Long roundId,
            @RequestBody DriveRoundRequest request) {

        return driveRoundService.updateRound(
                roundId,
                request
        );
    }



    @DeleteMapping("/rounds/{roundId}")
    public String deleteRound(
            @PathVariable Long roundId) {

        driveRoundService.deleteRound(roundId);

        return "Round deleted successfully";
    }
}