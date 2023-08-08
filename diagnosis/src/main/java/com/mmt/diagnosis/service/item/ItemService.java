package com.mmt.diagnosis.service.item;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<TestItems> findItems(List<Long> itemIdList){
        List<TestItems> test = itemRepository.findItems(itemIdList);
        //System.out.println("아이템 서비스에서 받은 itemIdList 사이즈" + test.size());
        return test;
    }

}
