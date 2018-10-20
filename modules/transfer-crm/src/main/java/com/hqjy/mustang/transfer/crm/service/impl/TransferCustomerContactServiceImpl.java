package com.hqjy.mustang.transfer.crm.service.impl;

import com.hqjy.mustang.common.base.base.BaseServiceImpl;
import com.hqjy.mustang.common.base.constant.Constant;
import com.hqjy.mustang.common.base.utils.PageQuery;
import com.hqjy.mustang.common.base.utils.StringUtils;
import com.hqjy.mustang.transfer.crm.dao.TransferCustomerContactDao;
import com.hqjy.mustang.transfer.crm.model.dto.TransferCustomerContactDTO;
import com.hqjy.mustang.transfer.crm.model.dto.TransferCustomerDTO;
import com.hqjy.mustang.transfer.crm.model.entity.TransferCustomerContactEntity;
import com.hqjy.mustang.transfer.crm.model.entity.TransferCustomerEntity;
import com.hqjy.mustang.transfer.crm.service.TransferCustomerContactService;
import com.hqjy.mustang.transfer.crm.service.TransferCustomerService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.hqjy.mustang.common.web.utils.ShiroUtils.getUserId;
import static com.hqjy.mustang.common.web.utils.ShiroUtils.getUserName;

@Service
public class TransferCustomerContactServiceImpl extends BaseServiceImpl<TransferCustomerContactDao, TransferCustomerContactEntity, Integer> implements TransferCustomerContactService {

    @Autowired
    private TransferCustomerService transferCustomerService;

    /**
     * 根据联系方式和详情，查询具体信息
     *
     * @author HSS 2018-06-25
     */
    @Override
    public TransferCustomerContactEntity getByDetail(Integer type, String detail) {
        return baseDao.findOneByDetail(type, detail);
    }

    @Override
    public int save(TransferCustomerContactEntity entity) {
        TransferCustomerEntity customerEntity = transferCustomerService.findOne(entity.getCustomerId());
        if (entity.getType().equals(Constant.CustomerContactType.WE_CHAT.getValue()) && customerEntity.getWeChat() == null) {
            transferCustomerService.update(new TransferCustomerEntity()
                            .setCustomerId(entity.getCustomerId()).setWeChat(entity.getDetail())
                            .setUpdateUserId(getUserId()).setUpdateUserName(getUserName())
                            );
        } else if (entity.getType().equals(Constant.CustomerContactType.LAND_LINE.getValue()) && customerEntity.getLandLine() == null) {
            transferCustomerService.update(new TransferCustomerEntity()
                    .setCustomerId(entity.getCustomerId()).setLandLine(entity.getDetail())
                    .setUpdateUserId(getUserId()).setUpdateUserName(getUserName())
            );
        } else if (entity.getType().equals(Constant.CustomerContactType.QQ.getValue()) && customerEntity.getQq() == null) {
            transferCustomerService.update(new TransferCustomerEntity()
                    .setCustomerId(entity.getCustomerId()).setQq(entity.getDetail())
                    .setUpdateUserId(getUserId()).setUpdateUserName(getUserName())
            );
        }
        entity.setCreateUserId(getUserId());
        entity.setCreateUserName(getUserName());
        entity.setProId(customerEntity.getProId());
        return super.save(entity);
    }

    @Override
    @Transactional
    public int save(TransferCustomerDTO dto) {
        int i = 0;
        if (StringUtils.isNotEmpty(dto.getPhone())) {
            i = baseDao.save(
                    new TransferCustomerContactEntity()
                            .setCustomerId(dto.getCustomerId())
                            .setProId(dto.getProId())
                            .setType(Constant.CustomerContactType.PHONE.getValue())
                            .setDetail(dto.getPhone())
                            .setCreateUserId(getUserId())
                            .setCreateUserName(getUserName())
            );
        }
        if (StringUtils.isNotEmpty(dto.getQq())) {
            i = baseDao.save(
                    new TransferCustomerContactEntity()
                            .setCustomerId(dto.getCustomerId())
                            .setProId(dto.getProId())
                            .setType(Constant.CustomerContactType.QQ.getValue())
                            .setDetail(dto.getQq())
                            .setCreateUserId(getUserId())
                            .setCreateUserName(getUserName())
            );
        }
        if (StringUtils.isNotEmpty(dto.getWeiXin())) {
            i = baseDao.save(
                    new TransferCustomerContactEntity()
                            .setCustomerId(dto.getCustomerId())
                            .setProId(dto.getProId())
                            .setType(Constant.CustomerContactType.WE_CHAT.getValue())
                            .setDetail(dto.getWeiXin())
                            .setCreateUserId(getUserId())
                            .setCreateUserName(getUserName())
            );
        }
        if (StringUtils.isNotEmpty(dto.getLandLine())) {
            i = baseDao.save(
                    new TransferCustomerContactEntity()
                            .setProId(dto.getProId())
                            .setCustomerId(dto.getCustomerId())
                            .setType(Constant.CustomerContactType.LAND_LINE.getValue())
                            .setDetail(dto.getLandLine())
                            .setCreateUserId(getUserId())
                            .setCreateUserName(getUserName())
            );
        }
        return i;
    }

    /**
     * 根据联系方式精确获取客户Id,供客户管理和客服管理分页查询条件刷选
     *
     * @param pageQuery 分页参数对象
     * @return 返回处理后的参数对象
     * @author gmm 2018年9月25日14:45:09
     */
    @Override
    public PageQuery setCustomerIdByContact(PageQuery pageQuery) {
        AtomicInteger integer = new AtomicInteger();
        if (StringUtils.isNotEmpty(MapUtils.getString(pageQuery, "phone"))) {
            TransferCustomerContactEntity contactEntity = this.getByDetail(Constant.CustomerContactType.PHONE.getValue(), MapUtils.getString(pageQuery, "phone"));
            if (contactEntity != null) {
                pageQuery.put("customerId", contactEntity.getCustomerId());
                return pageQuery;
            }
            integer.incrementAndGet();
        } else if (StringUtils.isNotEmpty(MapUtils.getString(pageQuery, "landLine"))) {
            TransferCustomerContactEntity contactEntity = this.getByDetail(Constant.CustomerContactType.LAND_LINE.getValue(), MapUtils.getString(pageQuery, "landLine"));
            if (contactEntity != null) {
                pageQuery.put("customerId", contactEntity.getCustomerId());
                return pageQuery;
            }
            integer.incrementAndGet();
        } else if (StringUtils.isNotEmpty(MapUtils.getString(pageQuery, "qq"))) {
            TransferCustomerContactEntity contactEntity = this.getByDetail(Constant.CustomerContactType.QQ.getValue(), MapUtils.getString(pageQuery, "qq"));
            if (contactEntity != null) {
                pageQuery.put("customerId", contactEntity.getCustomerId());
                return pageQuery;
            }
            integer.incrementAndGet();
        } else if (StringUtils.isNotEmpty(MapUtils.getString(pageQuery, "weiXin"))) {
            TransferCustomerContactEntity contactEntity = this.getByDetail(Constant.CustomerContactType.WE_CHAT.getValue(), MapUtils.getString(pageQuery, "weiXin"));
            if (contactEntity != null) {
                pageQuery.put("customerId", contactEntity.getCustomerId());
                return pageQuery;
            }
            integer.incrementAndGet();
        }
        //通过联系方式查询
        if (integer.get() > 0) {
            pageQuery.put("customerId", -1L);
        }
        return pageQuery;
    }

    @Override
    public List<TransferCustomerContactEntity> findListByCustomerIdBatch(String customerIds) {
        return baseDao.findListByCustomerIdBatch(customerIds);
    }

    @Override
    public List<TransferCustomerContactDTO> findByCustomerIds(String customerIds) {
        List<TransferCustomerContactEntity> list = this.findListByCustomerIdBatch(customerIds);
        List<TransferCustomerContactDTO> dtoList = new ArrayList<>();
        Map<Long, List<TransferCustomerContactEntity>> listMap = list.stream().collect(Collectors.groupingBy(TransferCustomerContactEntity::getCustomerId));
        listMap.forEach((x, y) -> {
            TransferCustomerContactDTO dto = new TransferCustomerContactDTO();
            dto.setCustomerId(x);
            y.forEach(c -> {
                if (c.getType().intValue() == Constant.CustomerContactType.PHONE.getValue()) {
                    String encryptPhone = StringUtils.encryptPhone(c.getDetail());
                    dto.setPhone(StringUtils.isEmpty(dto.getPhone()) ? encryptPhone : encryptPhone + ";" + dto.getPhone());
                }
                if (c.getType().intValue() == Constant.CustomerContactType.WE_CHAT.getValue()) {
                    dto.setWeiXin(StringUtils.isEmpty(dto.getWeiXin()) ? c.getDetail() : c.getDetail() + ";" + dto.getWeiXin());
                }
                if (c.getType().intValue() == Constant.CustomerContactType.QQ.getValue()) {
                    dto.setQq(StringUtils.isEmpty(dto.getQq()) ? c.getDetail() : c.getDetail() + ";" + dto.getQq());
                }
                if (c.getType().intValue() == Constant.CustomerContactType.EMAIL.getValue()) {
                    dto.setEmail(StringUtils.isEmpty(dto.getEmail()) ? c.getDetail() : c.getDetail() + ";" + dto.getEmail());
                }
                if (c.getType().intValue() == Constant.CustomerContactType.LAND_LINE.getValue()) {
                    dto.setLandLine(StringUtils.isEmpty(dto.getLandLine()) ? c.getDetail() : c.getDetail() + ";" + dto.getLandLine());
                }
            });
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<TransferCustomerContactEntity> findListByCustomerId(Long customerId) {
        return baseDao.findListByCustomerId(customerId);
    }

}
