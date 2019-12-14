package com.mozhengfly.boot.storage.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.mozhengfly.boot.storage.model.StoreResponse;
import com.mozhengfly.boot.storage.properties.AliyunProperties;
import com.mozhengfly.boot.storage.service.IStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @Description 阿里云Storage实现类
 * @Author mozhengfly
 * @Date 2018-12-07 00:04:08
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
public class AliyunStorageImpl implements IStorage {

    @Autowired
    private AliyunProperties aliyun;

    @Override
    public StoreResponse storeFile(String localPath) {
        log.debug("upload local file [{}]", localPath);
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        PutObjectResult result = ossClient.putObject(aliyun.getBucket(), aliyun.getRemoteDirectory() + FilenameUtils.getName(localPath), new File(localPath));
        log.info(result.getETag());
        ossClient.shutdown();
        return null;
    }
}
