/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.tool.util
 * @className com.mozhengfly.boot.tool.util.ToolUtil
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.tool.util;

import com.mozhengfly.boot.tool.ICallBack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ToolUtil
 * @Description ToolUtil
 * @Author wangchonglin
 * @Date 2021-03-09 11:47:37
 * @Version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ToolUtil {

    /**
     * 分批次处理数据
     * @param list  原始数据
     * @param size  每批多少个
     * @param callBack  回调处理方式
     * @param <T>
     */
    public static <T> void batchHandle(List<T> list, long size, ICallBack<T> callBack) {
        long limit = (list.size() + size - 1) / size;
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            List<T> collect = list.stream().skip(i * size).limit(size).collect(Collectors.toList());
            callBack.call(collect);
        });
    }
}
