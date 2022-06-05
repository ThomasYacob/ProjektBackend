package com.spring.backend.controller;

import com.spring.backend.model.Daily;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller class of the "Scoreboard" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Tag(name = "scoreboard", description = "Operations about the Scoreboard.")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/scoreboard")
public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

    @Operation(summary = "Get Scoreboards", description = "Retrieves all Scoreboards saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("all")
    public List<Scoreboard> getAllScoreboard(){
        return this.scoreBoardService.getAllScoreBoard();
    }

    @Operation(summary = "Get Scoreboard by ID", description = "Retrieves a specific User's Scoreboard, based on User ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("{id}")
    public Scoreboard getUserScoreboard(@PathVariable Long id){
        return scoreBoardService.getScoreForUser(id);
    }
}
