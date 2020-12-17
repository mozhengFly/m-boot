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
     * @return
     */
    boolean tryAcquire(String url);

    /**
     * 增加流量控制
     * @param url
     * @param permitsPerSecond
     */
    void addRateLimit(String url, double permitsPerSecond);
}
