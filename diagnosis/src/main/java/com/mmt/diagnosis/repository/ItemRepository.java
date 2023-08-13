package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.TestItems;

import java.util.List;

public interface ItemRepository {

    public List<TestItems> findItems(List<Long> itemIdList);

}
