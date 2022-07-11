package com.timain.controller;

import com.timain.result.AjaxResult;
import com.timain.service.MsmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yyf
 * @version 1.0
 * @date 2022/7/11 19:21
 */
@RestController
@RequestMapping("sms")
public class MsmController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String SUCCESS_CODE = "OK";

    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public AjaxResult sendMessage(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.hasText(code)) {
            return AjaxResult.success(200, "验证码未过期，请勿重复发送");
        }
        code = String.valueOf(new Random().nextInt(10000));
        Map<String, Object> params = new HashMap<>(8);
        params.put("code", code);
        try {
            String msg = msmService.sendMessage(params, phone);
            if (SUCCESS_CODE.equals(msg)) {
                redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
                return AjaxResult.success(200, "短信发送成功");
            } else {
                return AjaxResult.fail(msg);
            }
        } catch (Exception e) {
            LOGGER.error("send message error, phone: {}, msg: {}", phone, e.getMessage());
            return AjaxResult.fail(e.getMessage());
        }
    }
}
