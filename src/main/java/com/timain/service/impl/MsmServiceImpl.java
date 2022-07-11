package com.timain.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.timain.config.MsmConfig;
import com.timain.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yyf
 * @version 1.0
 * @date 2022/7/11 19:13
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private MsmConfig msmConfig;

    /**
     * 阿里云发送短信
     *
     * @param params 发送短信内容
     * @param phone  手机号
     * @return 返回值
     * @throws Exception java.lang.Exception
     */
    @Override
    public String sendMessage(Map<String, Object> params, String phone) throws Exception {
        SendSmsRequest request = new SendSmsRequest().setSignName(msmConfig.getSignName())
                .setPhoneNumbers(phone)
                .setTemplateCode(msmConfig.getTemplateCode())
                .setTemplateParam(JSON.toJSONString(params));
        SendSmsResponse response = msmConfig.client().sendSms(request);
        return response.body.message;
    }
}
