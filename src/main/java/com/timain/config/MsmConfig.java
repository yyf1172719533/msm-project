package com.timain.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yyf
 * @version 1.0
 * @date 2022/7/11 19:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.msm")
public class MsmConfig {

    /**
     * AccessKey ID
     */
    private String accessKeyId;

    /**
     * AccessKey Secret
     */
    private String accessKeySecret;

    /**
     * 签名
     */
    private String signName;

    /**
     * 模板code
     */
    private String templateCode;

    public Client client() throws Exception {
        Config config = new Config().setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        return new Client(config);
    }
}
