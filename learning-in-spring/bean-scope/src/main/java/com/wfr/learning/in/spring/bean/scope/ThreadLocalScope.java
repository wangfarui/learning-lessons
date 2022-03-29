package com.wfr.learning.in.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于 {@link Scope} 自定义Spring Bean作用域
 *
 * @author wangfarui
 * @since 2022/3/22
 */
public class ThreadLocalScope implements Scope {

    public static final String THREAD_LOCAL_SCOPE_NAME = "thread-local";

    private NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local-scope") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = getContext();
        Object o = map.get(name);
        if (o == null) {
            o = objectFactory.getObject();
            map.put(name, o);
        }
        return o;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
//        System.out.printf("registerDestructionCallback: name - %s, callback - %s\n", name, callback.hashCode());
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }

    private Map<String, Object> getContext() {
        return threadLocal.get();
    }
}
