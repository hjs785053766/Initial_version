package com.forezp.api.service;

import com.forezp.api.util.Notice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeignServic {
    @GetMapping(value = "/home")
    Notice home(@RequestParam(value = "name") String name);
}
