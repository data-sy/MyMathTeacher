package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.scenario.ScenarioRequest;
import com.mmt.diagnosis.service.scenario.ScenarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

//    @PostMapping("")
//    public ResponseEntity<ScenarioRequest> scenario(@RequestBody ScenarioRequest request) {
//        return ResponseEntity.ok(request);
//    }

    @PostMapping("")
    public void create(@RequestBody ScenarioRequest request) {
        scenarioService.create(request);
    }

}

