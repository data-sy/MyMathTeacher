package com.mmt.diagnosis.service.personal;

import com.mmt.diagnosis.domain.Item;
import com.mmt.diagnosis.dto.personal.ItemRequest;
import com.mmt.diagnosis.dto.personal.ItemResponse;

import java.util.ArrayList;
import java.util.List;

public class PersonalConverter {

    public static ItemResponse convertToItemResponse(ItemRequest request){
        ItemResponse response = new ItemResponse();
        response.setItemId(request.getItemId());
        response.setItemImagePath(request.getItemImagePath());
        response.setConceptId(request.getConceptId());
        response.setConceptName(request.getConceptName());
        response.setToConceptDepth(0);
        response.setProbabilityPercent(request.getProbabilityPercent());
        return response;
    }

    public static List<ItemResponse> convertListToItemResponseList(List<ItemRequest> requestList) {
        List<ItemResponse> responseList = new ArrayList<>();
        for (ItemRequest request : requestList) {
            responseList.add(convertToItemResponse(request));
        }
        return responseList;
    }

    public static ItemResponse convertToItemResponse(Item item){
        ItemResponse response = new ItemResponse();
        response.setItemId(item.getItemId());
        response.setItemImagePath(item.getItemImagePath());
        response.setConceptId(item.getConceptId());
        response.setConceptName(item.getConceptName());
        response.setToConceptDepth(item.getToConceptDepth());
        response.setProbabilityPercent(item.getProbabilityPercent());
        return response;
    }

    public static List<ItemResponse> convertItemListToItemResponseList(List<Item> itemList) {
        List<ItemResponse> responseList = new ArrayList<>();
        for (Item item : itemList) {
            responseList.add(convertToItemResponse(item));
        }
        return responseList;
    }

}
