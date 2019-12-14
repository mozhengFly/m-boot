package com.mozhengfly.boot.storage.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description 存储返回的结果数据
 * @Author mozhengfly
 * @Date 2018-12-06 23:29:46
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Data
public class StoreResponse {

    /** store前缀 aliyun-ftp-qiniuyun **/
    private String prefix;

    /** 存储空间位置 **/
    private String bucket;

    /** 文件大小 **/
    private long size;

    /** 远端存储目录 **/
    private String remoteDirectory;

    /** 远端存储文件名称 **/
    private String remoteNme;

    /** 原始文件名称 **/
    private String originName;

    /** 存储文件时间 **/
    private Date storeTime;
}
