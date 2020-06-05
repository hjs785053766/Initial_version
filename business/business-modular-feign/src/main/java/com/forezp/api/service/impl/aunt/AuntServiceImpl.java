package com.forezp.api.service.impl.aunt;

import com.forezp.api.entity.Aunt;
import com.forezp.api.mapper.AuntMapper;
import com.forezp.api.service.AuntService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forezp.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 清洁阿姨表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
@Service
@RestController
@Api(tags = "清洁阿姨表接口")
public class AuntServiceImpl extends ServiceImpl<AuntMapper, Aunt> implements AuntService {

    @Override
    @ResponseBody
    @ApiOperation("测试多修改")
    public Notice updAllRequestRecord(Aunt aunt) {
        return new Notice(HttpStatus.OK, baseMapper.updateById(aunt) != 0 ? true : false, "成功");
    }
}
