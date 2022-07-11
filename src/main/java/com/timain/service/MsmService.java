package com.timain.service;

import java.util.Map;

/**
 * @author yyf
 * @version 1.0
 * @date 2022/7/11 19:12
 */
public interface MsmService {

    /**
     * 阿里云发送短信
     * @param params 发送短信内容
     * @param phone 手机号
     * @return 返回值
     * @throws Exception java.lang.Exception
     */
    String sendMessage(Map<String, Object> params, String phone) throws Exception;
}
