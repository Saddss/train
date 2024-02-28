package com.sss.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjUtil;
import com.sss.train.common.context.LoginMemberContext;
import com.sss.train.common.util.SnowUtil;
import com.sss.train.member.domain.Passenger;
import com.sss.train.member.domain.PassengerExample;
import com.sss.train.member.mapper.PassengerMapper;
import com.sss.train.member.req.PassengerQueryReq;
import com.sss.train.member.req.PassengerSaveReq;
import com.sss.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq req){
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        List<Passenger> passengersList = passengerMapper.selectByExample(passengerExample);
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengersList, PassengerQueryResp.class);
        return passengerQueryResps;
    }
}
