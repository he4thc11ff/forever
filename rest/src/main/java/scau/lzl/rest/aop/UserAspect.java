package scau.lzl.rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Aspect
@Component
public class UserAspect {

    /**
     * 定义切入点，切入点为scau.lizl.forever.controller.UserController中的所有函数
     */
    @Pointcut("execution(public * scau.lzl.rest.controller.UserController.*(..))")
    public void definePoint() {}

//    /**
//     * 在切入点执行前执行
//     */
//    @Before("definePoint()")
//    public void doBefore() {
//        System.out.println("UserAspect.doBefore");
//    }
//
//    /**
//     * 在切入点执行后执行（无论是执行成功还是抛出异常）
//     */
//    @After("definePoint()")
//    public void doAfter() {
//        System.out.println("UserAspect.doAfter");
//    }
//
//    /**
//     * 在切入点执行后执行（执行成功）
//     */
//    @AfterReturning("definePoint()")
//    public void doAfterReturn() {
//        System.out.println("UserAspect.doAfterReturn");
//    }
//
//    /**
//     * 在切入点执行后执行（执行不成功，抛出异常）
//     */
//    @AfterThrowing("definePoint()")
//    public void doAfterThrowEx() {
//        System.out.println("UserAspect.doAfterThrowEx");
//    }

    /**
     * 环绕通知，将 切入点point 传进来，简化写法，不用单独写 @After 之类的方法了（如果写了就先执行doAround，再执行其它）
     * @param point
     */
    @Around("definePoint()")
    public Object doAround(ProceedingJoinPoint point) {
        Object result = null;

        System.out.println("point.getArgs() -> " + Arrays.asList(point.getArgs()));
        System.out.println("point.getKind() -> " + point.getKind());
        System.out.println("point.getSignature() -> " + point.getSignature());
        System.out.println("point.getSignature().getDeclaringType() -> " + point.getSignature().getDeclaringType());
        System.out.println("point.getSignature().getDeclaringTypeName() -> " + point.getSignature().getDeclaringTypeName());
        System.out.println("point.getSignature().getModifiers() -> " + point.getSignature().getModifiers());
        System.out.println("point.getSignature().getName() -> " + point.getSignature().getName());
        System.out.println("point.getSourceLocation() -> " + point.getSourceLocation());
        System.out.println("point.getStaticPart() -> " + point.getStaticPart());
        System.out.println("point.getTarget() -> " + point.getTarget());
        System.out.println("point.getThis() -> " + point.getThis());
        System.out.println("point.getClass() -> " + point.getClass());

        try {
            System.out.println("doAround.before");
            result = point.proceed();
            Object[] args = point.getArgs();
            for (Object arg: args) {
                HttpSession session = (HttpSession) arg;
                System.out.println(session.getAttribute("user"));
            }
            System.out.println("doAround.after");
        } catch (Throwable throwable) {
            System.out.println("doAround.catchException");
        }

        return result;
    }
}
