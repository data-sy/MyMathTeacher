package com.mmt.diagnosis.service.personal;

import com.mmt.diagnosis.dto.personal.ItemResponse;
import com.mmt.diagnosis.dto.personal.PersonalGetRequest;
import com.mmt.diagnosis.dto.personal.PersonalResponse;
import com.mmt.diagnosis.repository.ProbabilityRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PersonalService {

    private final ProbabilityRepository probabilityRepository;

    public PersonalService(ProbabilityRepository probabilityRepository) {
        this.probabilityRepository = probabilityRepository;
    }

    public PersonalResponse findPersonalization(PersonalGetRequest request){
        Long studentTestId = request.getStudentTestId();
        PersonalResponse response = new PersonalResponse();
        response.setStudentTestId(studentTestId);
        response.setStudentName(request.getStudentName());
        response.setStudentBirthdate(request.getStudentBirthdate());
        HashMap<String, List<ItemResponse>> itemResponseHashMap = new HashMap<>();
        // 뎁스 0
        itemResponseHashMap.put("depth0", PersonalConverter.convertListToItemResponseList(request.getItemRequestList()));
        // 뎁스 1, 2
        itemResponseHashMap.put("depth1", PersonalConverter.convertItemListToItemResponseList(probabilityRepository.findItem(studentTestId, 1)));
        itemResponseHashMap.put("depth2", PersonalConverter.convertItemListToItemResponseList(probabilityRepository.findItem(studentTestId, 2)));
        response.setItemResponseHashMap(itemResponseHashMap);
        response.setConceptNameList(request.getConceptNameList());
        response.setToConceptNameList(request.getToConceptNameList());
        response.setFromConceptNameList(request.getFromConceptNameList());
        return response;
    }

}
