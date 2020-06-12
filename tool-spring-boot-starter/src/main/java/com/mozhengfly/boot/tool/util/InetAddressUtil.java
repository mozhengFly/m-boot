package com.mozhengfly.boot.tool.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddressUtil
 *
 * @Description InetAddressUtil
 * @Author mozhengfly
 * @Date 2020-06-12 21:28:07
 * @Version V1.0.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InetAddressUtil {

    /**
     * 获取当前IP地址
     * @return
     */
    public static String getCurrentIpAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取IP地址出错.", e);
            return null;
        }
    }
}
