/**
 * @projectName build
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.rate.IRateStrategy
 * @copyright Copyright 2020 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

/**
 * IRateStrategy
 *
 * @Description 限流策略
 * @Author wangchonglin
 * @Date 2020-12-15 18:59:37
 * @Version 1.0.0
 */
public interface IRateStrategy {

    /**
     * 限流策略
     * @param url 访问url
     * @param method 访问method
     * @return
     */
    boolean tryAcquire(String url, String method);

    /**
     * 增加流量控制
     * @param url
     * @param method
     * @param permitsPerSecond
     */
    void addRateLimit(String url, String method, double permitsPerSecond);
}
