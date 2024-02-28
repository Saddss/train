package com.sss.train.member.controller;

import com.sss.train.common.context.LoginMemberContext;
import com.sss.train.common.resp.CommonResp;
import com.sss.train.member.req.PassengerQueryReq;
import com.sss.train.member.req.PassengerSaveReq;
import com.sss.train.member.resp.PassengerQueryResp;
import com.sss.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<Object> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

}
