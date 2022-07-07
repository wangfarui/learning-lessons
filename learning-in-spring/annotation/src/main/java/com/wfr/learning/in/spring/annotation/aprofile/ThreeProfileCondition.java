package com.wfr.learning.in.spring.annotation.aprofile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * profile = "three" 的条件判断
 *
 * @author wangfarui
 * @since 2022/7/7
 */
public class ThreeProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles(t -> t.test("three"));
    }
}
