package com.mozhengfly.boot.tool.util;

import com.mozhengfly.boot.tool.common.SnowFlake;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SnowFlakeUtil
 *
 * @Description SnowFlakeUtil
 * @Author wangchonglin
 * @Date 2020-08-05 15:02:28
 * @Version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SnowFlakeUtil {

    private static final SnowFlake snowFlake = new SnowFlake(2,3);

    /**
     * 生成ID
     * @return
     */
    public static long generateId() {
        return snowFlake.nextId();
    }

}
