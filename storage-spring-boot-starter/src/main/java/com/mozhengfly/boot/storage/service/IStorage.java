package com.mozhengfly.boot.storage.service;


import com.mozhengfly.boot.storage.model.StoreResponse;

/**
 * @Description 定义Storage接口
 * @Author mozhengfly
 * @Date 2018-12-06 23:32:54
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public interface IStorage {

    /**
     * 保存本地文件
     * @param localPath
     * @return
     */
    StoreResponse storeFile(String localPath);
}
