package com.hqjy.mustang.transfer.crm.dao;

import com.hq.mustang.common.base.BaseDao;
import com.hqjy.mustang.transfer.crm.entity.TransferCustomerDealEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * transfer_customer_deal 持久化层
 * 
 * @author : xyq
 * @date : 2018/09/07 10:16
 */
@Mapper
public interface TransferCustomerDealDao extends BaseDao<TransferCustomerDealEntity, Long> {
}