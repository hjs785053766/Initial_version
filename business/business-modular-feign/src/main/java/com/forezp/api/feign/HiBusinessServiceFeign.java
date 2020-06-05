package com.forezp.api.feign;

import com.forezp.api.service.BusinessService;
import com.forezp.api.util.FeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "business-modular-hi" , contextId = "HiBusinessServiceFeign",configuration = FeignServiceConfig.class)
public interface HiBusinessServiceFeign extends BusinessService {
}
