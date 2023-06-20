package com.example.springbasic.scan.filter;

import java.lang.annotation.*;

/**
 * @author junyeong.jo .
 * @since 2023-06-20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
