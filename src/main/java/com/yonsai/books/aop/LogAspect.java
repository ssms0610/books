package com.yonsai.books.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect // 이 클래스는 AOP다 선언
@Component // 스프링이 자동으로 스캔해서 Bean으로 등록
public class LogAspect {

    // @Pointcut : 어떤 메서드에 AOP를 적용할지 대상 지정
    // -> controller 패키지의 모든 메서드
    @Pointcut("execution(* com.yonsai.books.controller.*.*(..))")
    public void controllerMethods() {
    }

    // 1. 메서드 실행 전 출력
    @Before("controllerMethods()")
    public void beforeLog() { // before 어드바이스 메서드 시작
        // 컨트롤러 어떤 메서드를 호출하던 무조건 먼저 출력되는 문구
        System.out.println("메서드 실행 전");
    }

    // 2. Around 전, 후 + 매개변수 + 반환값 출력
    @Around("controllerMethods()")
    // Object : 컨트롤러 메서드마다 반환 타입이 다양할 수 있어서 무엇을 반환하든 그대로 돌려주기 위해
    // throws Throwable : 내부에서 실행하는 메서드가 어떤 예외를 던질지 모르기 떄문에 가장 상위 예외로 처리
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // MethodSignature : 지금 실행될 메서드의 서명(정보)를 가져옴
        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
        String methodName = sig.getDeclaringType().getSimpleName() + "." + sig.getName();

        // 메서드에 전달된 매개변수 값을 배열로 꺼냄
        // -> 실행 전 로그 + 파라미터 값 로그 출력
        Object[] args = joinPoint.getArgs();
        System.out.println("[AOP - BEFORE]" + methodName);
        System.out.println("[AOP - ARGS]" + Arrays.toString(args));

        // 반환 값을 담을 변수 선언
        Object result = null;

        // 예외처리
        try {

            // 실제 메서드 실행
            result = joinPoint.proceed();

            // 메서드 실행이 정상 종료 -> 반환값 출력
            System.out.println("[AOP - BEFORE]" + result);
            System.out.println("[AOP - AFTER]" + methodName);

            return result;

        } catch (Throwable e) {

            // 예외 발생 -> 어떤 메서드에서 어떤 예외가 났는지 출력
            System.out.println("[AOP - EXCEPTION]" + methodName + "/" + e.getMessage());

            throw e;
        }
    }

}
