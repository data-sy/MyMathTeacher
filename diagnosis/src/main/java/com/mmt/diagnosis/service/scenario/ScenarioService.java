package com.mmt.diagnosis.service.scenario;

import com.mmt.diagnosis.domain.Scenario;
import com.mmt.diagnosis.dto.scenario.ScenarioRequest;
import com.mmt.diagnosis.repository.concept.ConceptRepository;
import com.mmt.diagnosis.repository.scenario.ScenarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Service
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public void create(ScenarioRequest request){
        int scenarioCase = request.getScenarioCase();
        int conceptId = request.getConceptId();
        List<Integer> checkList = request.getCheckList();
        // 중복 제거
        HashSet<Integer> uniqueIds = new HashSet<>(checkList);
        List<Integer> skillIds = new ArrayList<>(uniqueIds);
        List<List<Double>> probabilityList = request.getProbabilityList();

        // 각 스킬아이디마다 시나리오객체 생성해서 add
        List<Scenario> scenarioList = new ArrayList<>();
        for (Integer skillId : skillIds) {
            Scenario scenario = new Scenario();
            scenario.setScenarioCase(scenarioCase);
            scenario.setMomId(conceptId);
            scenario.setSkillId(skillId);

            List<Double> proList = new ArrayList<>();
            // [1, 100, 500, 1000] 순으로 들어있음
            for (List<Double> pro : probabilityList) {
                proList.add((pro.get(skillId - 1)));
            }
            scenario.setProList(proList.toString());
            scenarioList.add(scenario);
        }
        scenarioRepository.save(scenarioList);
    }





}
