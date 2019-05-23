package com.suixingpay.hw.web.service;

import com.suixingpay.hw.common.core.domain.RpcResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-20 17:09
 **/
@FeignClient(name= "CLOUD-FANG-SERVICE-USER")
public interface SaasRemoteService {

    @GetMapping(value = "/user/getDemoToken")
    RpcResponseBean<Map<String, String>> testLogin(@RequestParam(value = "response", required = false)  HttpServletResponse response);

}
