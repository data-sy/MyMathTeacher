package com.mmt.diagnosis.service.probability;

import com.mmt.diagnosis.domain.ItemProbability;
import com.mmt.diagnosis.dto.details.ItemProbabilityResponse;

import java.util.ArrayList;
import java.util.List;

public class ProbabilityConverter {

    public static ItemProbabilityResponse convertToItemProbabilityResponse(ItemProbability itemProbability){
        ItemProbabilityResponse itemProbabilityResponse = new ItemProbabilityResponse();
        itemProbabilityResponse.setTestItemNumber(itemProbability.getTestItemNumber());
        itemProbabilityResponse.setItemImagePath(itemProbability.getItemImagePath());
        itemProbabilityResponse.setConceptId(itemProbability.getConceptId());
        itemProbabilityResponse.setConceptName(itemProbability.getConceptName());
        itemProbabilityResponse.setProbabilityPercent(itemProbability.getProbabilityPercent());
        return itemProbabilityResponse;
    }

    public static List<ItemProbabilityResponse> convertListToItemProbabilityResponseList(List<ItemProbability> itemProbabilityList) {
        List<ItemProbabilityResponse> responseList = new ArrayList<>();
        for (ItemProbability itemProbability : itemProbabilityList) {
            responseList.add(convertToItemProbabilityResponse(itemProbability));
        }
        return responseList;
    }
}
