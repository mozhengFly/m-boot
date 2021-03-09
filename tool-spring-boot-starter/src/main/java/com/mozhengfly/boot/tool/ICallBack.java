/**
 * @projectName build
 * @package com.mozhengfly.boot.tool
 * @className com.mozhengfly.boot.tool.ICallBack
 * @copyright Copyright 2021 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.tool;


import java.util.List;

/**
 * ICallBack
 *
 * @Description ICallBack
 * @Author wangchonglin
 * @Date 2021-03-09 11:18:58
 * @Version 1.0.0
 */
@FunctionalInterface
public interface ICallBack<T> {

    /**
     * 回调处理
     *
     * @param list 批量数据
     */
    void call(List<T> list);
}
