package com.mmt.diagnosis.service.personal;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.dto.personal.*;
import com.mmt.diagnosis.repository.probability.ProbabilityRepository;
import com.mmt.diagnosis.service.item.ItemService;
import com.mmt.diagnosis.service.testItem.TestItemConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalService {

    private final ProbabilityRepository probabilityRepository;
    private final ItemService itemService;

    public PersonalService(ProbabilityRepository probabilityRepository, ItemService itemService) {
        this.probabilityRepository = probabilityRepository;
        this.itemService = itemService;
    }

    public PersonalItemsResponse findPersonalItems(PersonalItemsGetRequest request){
        Long studentTestId = request.getStudentTestId();
        PersonalItemsResponse response = new PersonalItemsResponse();
        response.setStudentTestId(studentTestId);
        response.setStudentName(request.getStudentName());
        response.setStudentBirthdate(request.getStudentBirthdate());
        Map<String, List<ItemResponse>> itemResponseMap = new HashMap<>();
        // 뎁스 0
        itemResponseMap.put("depth0", PersonalConverter.convertItemListToItemResponseList(probabilityRepository.findItems(studentTestId, 0)));
        // 뎁스 1, 2
        itemResponseMap.put("depth1", PersonalConverter.convertItemListToItemResponseList(probabilityRepository.findItems(studentTestId, 1)));
        itemResponseMap.put("depth2", PersonalConverter.convertItemListToItemResponseList(probabilityRepository.findItems(studentTestId, 2)));
        response.setItemResponseMap(itemResponseMap);
        response.setItemIdList(PersonalConverter.abstractItemId(request.getItemRequestList()));
        response.setConceptNameList(request.getConceptNameList());
        response.setToConceptNameList(request.getToConceptNameList());
        response.setFromConceptNameList(request.getFromConceptNameList());
        return response;
    }

    public PersonalTestResponse preview(PersonalTestGetRequest request){
        PersonalTestResponse response = new PersonalTestResponse();
        // 리팩토링 : 프론트 단에서 이동시키면 없어질 필드들
        response.setStudentName(request.getStudentName());
        response.setNewTestName(request.getNewTestName());
        response.setTestName(request.getTestName());
        response.setStudentTestId(request.getStudentTestId());

        Map<Long, Integer> itemNumMap = request.getItemNumMap();
        List<Long> itemIdList = new ArrayList<>(itemNumMap.keySet());
        List<TestItems> testItemsList = itemService.findItems(itemIdList);
        for (TestItems item : testItemsList){
            item.setTestItemNumber(itemNumMap.get(item.getItemId()));
        }
        response.setTestItemsResponseList(TestItemConverter.convertListToTestItemsResponseList(testItemsList));
        return response;
    }
}
