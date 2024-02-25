package com.sss.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.jwt.JWTUtil;
import com.sss.train.common.exception.BusinessException;
import com.sss.train.common.exception.BusinessExceptionEnum;
import com.sss.train.common.util.SnowUtil;
import com.sss.train.member.domain.Member;
import com.sss.train.member.domain.MemberExample;
import com.sss.train.member.mapper.MemberMapper;
import com.sss.train.member.req.MemberLoginReq;
import com.sss.train.member.req.MemberRegisterReq;
import com.sss.train.member.req.MemberSendCodeReq;
import com.sss.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);


    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        Member memberDB = getMembers(mobile);

        if (ObjUtil.isNotNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        Member memberDB = getMembers(mobile);

        if (ObjUtil.isNull(memberDB)){
            LOG.info("手机号不存在，插入记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号已存在，不插入记录");
        }

        //生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);


        //保存短信记录表功能
        //不在开发但是说明一下表结构
        //手机号、短信验证码、有效期、是否已使用、业务类型、发送时间、使用时间


        //对接短信通道、发送短信
    }
    public MemberLoginResp login(MemberLoginReq req){
        String mobile = req.getMobile();
        Member memberDB = getMembers(mobile);

        if (ObjUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        //校验短信验证码
        if (!"8888".equals(req.getCode())){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        Map<String, Object> map = BeanUtil.beanToMap(memberLoginResp);
        String key = "Saddss";
        String token = JWTUtil.createToken(map, key.getBytes());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    private Member getMembers(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        //手机号不存在则插入记录
        if (CollUtil.isEmpty(members)){
            return null;
        } else {
            return members.get(0);
        }

    }

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
