package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.domain.Item2;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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

    public void save1(String abc1) {
        itemMapper.save1(abc1);
    }



    public Item2 find(String id) {
        return itemMapper.find(id);
    }
    public Item2 findidpw(String id, String pw) {
        return itemMapper.findidpw(id,pw);
    }
    public void update1(Item2 ttt) {
        itemMapper.update1(ttt);
    }

    public void delete(String ccc) {itemMapper.delete(ccc);}

}
