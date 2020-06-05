package com.forezp.api.service.impl.administratorextraattribute;

import com.forezp.api.entity.Administratorextraattribute;
import com.forezp.api.entity.Aunt;
import com.forezp.api.entity.Business;
import com.forezp.api.feign.HiBusinessServiceFeign;
import com.forezp.api.mapper.AdministratorextraattributeMapper;
import com.forezp.api.service.AdministratorextraattributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forezp.api.service.AuntService;
import com.forezp.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 管理员额外属性表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-01
 */
@Service
@RestController
@Api(tags = "管理员额外属性表接口")
public class AdministratorextraattributeServiceImpl extends ServiceImpl<AdministratorextraattributeMapper, Administratorextraattribute> implements AdministratorextraattributeService {

    @Resource
    AuntService auntService;

    @Resource
    HiBusinessServiceFeign hiBusinessServiceFeign;

    @Override
    @ResponseBody
    @ApiOperation("测试多修改")
    public Notice updAllRequestRecord() {
        Administratorextraattribute administratorextraattribute = new Administratorextraattribute();
        administratorextraattribute.setId(4);
        administratorextraattribute.setSubstance("测试" + new Date().getTime());
        baseMapper.updateById(administratorextraattribute);

        Aunt aunt = new Aunt();
        aunt.setId(7);
        aunt.setName("测试" + new Date().getTime());
        auntService.updateById(aunt);

        Business business = new Business();
        business.setId(4);
        business.setName("测试" + new Date().getTime());
        hiBusinessServiceFeign.updAllRequestRecord(business);
        return new Notice(HttpStatus.OK, "成功");
    }
}
