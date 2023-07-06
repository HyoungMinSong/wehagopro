package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository{
    private final ItemMapper itemMapper;

    public Item save(Item item) {
        itemMapper.save(item);
        return item;
    }

    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemMapper.update(itemId, updateParam);
    }

    public Item findById(Long id) {
        return itemMapper.findById(id);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        return itemMapper.findAll(cond);
    }
}