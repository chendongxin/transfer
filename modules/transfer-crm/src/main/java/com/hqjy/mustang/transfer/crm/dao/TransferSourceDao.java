package com.hqjy.mustang.transfer.crm.dao;

import com.hqjy.mustang.common.base.base.BaseDao;
import com.hqjy.mustang.transfer.crm.model.entity.TransferSourceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * transfer_source 持久化层
 * 
 * @author : xyq
 * @date : 2018/09/14 11:19
 */
@Mapper
public interface TransferSourceDao extends BaseDao<TransferSourceEntity, Long> {
}