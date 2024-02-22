package com.sss.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.sss.train.common.exception.BusinessException;
import com.sss.train.common.exception.BusinessExceptionEnum;
import com.sss.train.common.util.SnowUtil;
import com.sss.train.member.domain.Member;
import com.sss.train.member.domain.MemberExample;
import com.sss.train.member.mapper.MemberMapper;
import com.sss.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(members)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
