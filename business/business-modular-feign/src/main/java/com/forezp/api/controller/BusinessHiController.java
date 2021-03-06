package com.forezp.api.controller;

import com.forezp.api.service.FeignServic;
import com.forezp.api.util.BaseApiService;
import com.forezp.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "feign服务")
@RestController
@RefreshScope
public class BusinessHiController extends BaseApiService implements FeignServic {

    @Value("${server.port}")
    String port;

    @ResponseBody
    @ApiOperation("调用hi服务")
    public Notice home(@RequestParam String name) {
        return new Notice(HttpStatus.OK, "成功");
//        return new Notice(HttpStatus.OK, "当前请求客户端端口号:" + port + "," + hiServiceFeign.home(name) + ",请求人：" + getUserName());
    }
}