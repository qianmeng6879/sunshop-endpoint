package top.mxzero.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JWTAuthentication {
    // 是否需要认证
    public boolean require() default true;

    // 根据角色认证
    public String role() default "";

    // 可执行的权限
    public String permission() default "";

}
