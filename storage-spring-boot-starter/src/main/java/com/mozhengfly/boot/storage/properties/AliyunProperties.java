package com.mozhengfly.boot.storage.properties;

import com.mozhengfly.boot.storage.constants.StorageConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description 阿里云配置类
 * @Author mozhengfly
 * @Date 2018-12-07 00:04:46
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Data
@ConfigurationProperties(prefix = StorageConst.A_LI_YUN_PREFIX)
public class AliyunProperties {

    /** 是否可用 **/
    private boolean enable;

    /** 阿里云EndPoint（地域节点）**/
    private String endPoint;

    /** 阿里云accessKeyId **/
    private String accessKeyId;

    /** 阿里云accessKeySecret **/
    private String accessKeySecret;

    /** 阿里云bucket **/
    private String bucket;

    /** 远端存储目录 **/
    private String remoteDirectory;

}
