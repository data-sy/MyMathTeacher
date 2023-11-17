package com.mmt.diagnosis.repository.scenario;

import com.mmt.diagnosis.domain.Scenario;

import java.util.List;

public interface ScenarioRepository {

    void save(List<Scenario> scenarioList);

}
