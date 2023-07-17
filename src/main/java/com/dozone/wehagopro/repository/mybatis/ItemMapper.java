package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.Item;
import com.dozone.wehagopro.repository.ItemSearchCond;
import com.dozone.wehagopro.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    void save(Item item);
    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);
    Item findById(Long id);
    List<Item> findAll(ItemSearchCond itemSearch);
    void delete(Long id);
}
