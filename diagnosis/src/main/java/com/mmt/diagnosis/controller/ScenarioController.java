package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.scenario.ScenarioRequest;
import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScenarioController {

    @PostMapping("/scenario")
    public ResponseEntity<ScenarioRequest> scenario(@RequestBody ScenarioRequest request) {
        return ResponseEntity.ok(request);
    }

}
