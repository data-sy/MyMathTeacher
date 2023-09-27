package com.mmt.diagnosis.service.item;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.repository.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<TestItems> findItems(List<Long> itemIdList){
        return itemRepository.findItems(itemIdList);
    }

}
