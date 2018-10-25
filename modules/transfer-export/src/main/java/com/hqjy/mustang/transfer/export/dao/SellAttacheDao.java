package com.hqjy.mustang.transfer.export.dao;

import com.hqjy.mustang.transfer.export.model.entity.CustomerEntity;
import com.hqjy.mustang.transfer.export.model.query.SellQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gmm
 * @date create on 2018/10/22
 * @apiNote 电销专员排行报表数据获取层
 */
@Component
@Mapper
public interface SellAttacheDao {

    /**
     * 统计电销专员上门量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countVisitBusiness(SellQueryParams params);

    /**
     * 统计电销专员有效商机量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countValidBusiness(SellQueryParams params);

    /**
     * 统计电销专员成交量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countDealBusiness(SellQueryParams params);

    /**
     * 统计电销专员被分配商机总量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countAllotBusiness(SellQueryParams params);

    /**
     * 统计电销专员今日预约上门量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countVisitTodayAppointBusiness(SellQueryParams params);

    /**
     * 统计电销专员明日预约上门量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countVisitTomoAppointBusiness(SellQueryParams params);

    /**
     * 统计电销专员有效上门量
     *
     * @param params 参数
     * @return 返回结果
     */
    List<CustomerEntity> countVisitValidBusiness(SellQueryParams params);
}
