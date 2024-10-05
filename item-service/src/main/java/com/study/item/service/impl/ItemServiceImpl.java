package com.study.item.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.api.dto.ItemDTO;
import com.study.api.dto.OrderDetailDTO;
import com.study.common.exception.BizIllegalException;
import com.study.common.utils.BeanUtils;
import com.study.item.domain.po.Item;
import com.study.item.mapper.ItemMapper;
import com.study.item.service.IItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.study.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足!", e);
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
