package com.spring.backend.controller;

import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/scoreboard")

public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

}
