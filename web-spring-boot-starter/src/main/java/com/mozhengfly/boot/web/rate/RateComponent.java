/**
 * @projectName data-synchronization
 * @package com.mozhengfly.personal.web.practice
 * @className com.mozhengfly.personal.web.practice.RateComponent
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RateComponent
 * @Description 限流
 * @Author wangchonglin
 * @Date 2020-12-14 19:03:11
 * @Version 1.0.0
 */
public class RateComponent {

    private Map<String, RateLimiter> rateMap = new ConcurrentHashMap();

    public boolean tryAcquire() {
        return rateMap.get("a").tryAcquire();
    }
}
