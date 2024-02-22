package com.sss.train.member.controller;

import com.sss.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberMapper memberMapper;

    @GetMapping("/count")
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
