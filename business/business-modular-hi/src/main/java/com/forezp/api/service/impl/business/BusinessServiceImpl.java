package com.forezp.api.service.impl.business;

import com.forezp.api.entity.Business;
import com.forezp.api.mapper.BusinessMapper;
import com.forezp.api.service.BusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forezp.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-02
 */
@Service
@RestController
@Api(tags = "商家表接口")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Override
    @ResponseBody
    @ApiOperation("测试修改")
    public Notice updAllRequestRecord(@RequestBody Business business) {
        return new Notice(HttpStatus.OK, baseMapper.updateById(business) != 0 ? true : false, "成功");
    }
}
