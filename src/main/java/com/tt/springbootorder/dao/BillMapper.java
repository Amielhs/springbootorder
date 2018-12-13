package com.tt.springbootorder.dao;

import com.tt.springbootorder.pojo.Bill;

import java.util.List;

public interface BillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Long id);

    List<Bill> selectAll();

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}