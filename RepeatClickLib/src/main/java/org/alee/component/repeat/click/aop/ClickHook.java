package org.alee.component.repeat.click.aop;

import android.view.View;

import org.alee.component.repeat.click.ClickDeduplication;
import org.alee.component.repeat.click.annotation.RepeatedClick;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
@Aspect
public final class ClickHook {

    private static final String CLASS_NAME_ANNOTATION = "org.alee.component.repeat.click.annotation.RepeatedClick";

    @Pointcut("execution(@" + CLASS_NAME_ANNOTATION + " * *(..))")
    public void hookOnClickByAnnotation() {

    }

    @Around("hookOnClickByAnnotation()")
    public void onClickByAnnotationProcessor(ProceedingJoinPoint joinPoint) throws Throwable {
        if (null == joinPoint.getArgs() || 0 >= joinPoint.getArgs().length) {
            joinPoint.proceed();
            return;
        }
        View view = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof View) {
                view = (View) o;
                break;
            }
        }
        if (null == view) {
            joinPoint.proceed();
            return;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (null == methodSignature) {
            joinPoint.proceed();
            return;
        }
        Method method = methodSignature.getMethod();
        if (null == method) {
            joinPoint.proceed();
            return;
        }
        RepeatedClick repeatedClick = method.getAnnotation(RepeatedClick.class);
        if (null == repeatedClick) {
            joinPoint.proceed();
            return;
        }
        if (ClickDeduplication.isFastDoubleClick(view,repeatedClick.interval())){
            return;
        }
        joinPoint.proceed();
    }
}
