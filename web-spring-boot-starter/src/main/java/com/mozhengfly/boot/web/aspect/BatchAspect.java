package com.mozhengfly.boot.web.aspect;

import com.mozhengfly.boot.web.annotation.BatchAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * BatchAspect
 * @Description BatchAspect
 * @Author wangchonglin
 * @Date 2020-08-05 16:47:17
 * @Version 1.0.0
 */
@Component
@Aspect
public class BatchAspect {

    @Around("@annotation(com.mozhengfly.boot.web.annotation.BatchAction)")
    public Object run(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        BatchAction batchAction = method.getAnnotation(BatchAction.class);
        int index = batchAction.index();
        int size = batchAction.size();
        // index 超长 不处理
        if (args.length <= index) {
            return joinPoint.proceed(args);
        }
        Object arg = args[index];
        List<Object> result = new ArrayList<>();
        if (arg instanceof Collection) {
            Collection a = (Collection) arg;
            Iterator iterator = a.iterator();
            List<Object> query = new ArrayList<>();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                query.add(next);
                if (query.size() % size == 0) {
                    doQuery(joinPoint, args, index, result, query);
                    query.clear();
                }
            }
            if (!CollectionUtils.isEmpty(query)) {
                doQuery(joinPoint, args, index, result, query);
            }
        }
        return result;
    }

    private void doQuery(ProceedingJoinPoint joinPoint, Object[] args, int index, List<Object> result, List<Object> query) throws Throwable {
        args[index] = query;
        Object proceed = joinPoint.proceed(args);
        if (proceed instanceof Collection) {
            result.addAll((Collection) proceed);
        }
    }
}
