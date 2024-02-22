package com.example.back.exback.aop;

import com.example.back.exback.trace.LogTrace;
import com.example.back.exback.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* com.example.back.exback.api..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

//    private TraceStatus status;
//
//    @Before("com.example.back.exback.aop.Pointcuts.apiAll()")
//    public void before(JoinPoint joinPoint) {
//        String message = joinPoint.getSignature().toShortString();
//        status = logTrace.begin(message);
//    }
//
//    @After(value = "com.example.back.exback.aop.Pointcuts.apiAll()")
//    public void after(JoinPoint joinPoint) {
//        logTrace.end(status);
//    }
//
//    @AfterReturning(value = "com.example.back.exback.aop.Pointcuts.apiAll()", returning = "result")
//    public void afterReturning(JoinPoint joinPoint, Object result) {
//        log.info("[after] {}, result={}", joinPoint.getSignature(), result);
//    }
//
//    @AfterThrowing(value = "com.example.back.exback.aop.Pointcuts.apiAll()", throwing = "ex")
//    public void afterThrowing(JoinPoint joinPoint, Exception ex) throws Exception {
//        logTrace.exception(status, ex);
//        throw ex;
//    }


}
