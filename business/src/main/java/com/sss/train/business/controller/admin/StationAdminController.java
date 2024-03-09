package com.sss.train.business.controller.admin;

import com.sss.train.common.resp.CommonResp;
import com.sss.train.common.resp.PageResp;
import com.sss.train.business.req.StationQueryReq;
import com.sss.train.business.req.StationSaveReq;
import com.sss.train.business.resp.StationQueryResp;
import com.sss.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return new CommonResp<>();
    }

}
