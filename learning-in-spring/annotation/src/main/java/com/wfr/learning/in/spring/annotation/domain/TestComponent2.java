package com.wfr.learning.in.spring.annotation.domain;

import com.wfr.learning.in.spring.annotation.acomponent.MyComponent2;
import org.springframework.stereotype.Component;

/**
 * 测试 {@link Component} 派生性
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@MyComponent2
public class TestComponent2 implements ITestComponent {
}
