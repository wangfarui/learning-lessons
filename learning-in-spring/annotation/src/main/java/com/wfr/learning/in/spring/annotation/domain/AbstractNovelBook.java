package com.wfr.learning.in.spring.annotation.domain;

/**
 * 小说书籍
 *
 * @author wangfarui
 * @since 2022/5/12
 */
public abstract class AbstractNovelBook implements IBook {

    @Override
    public String getPublishingHouse() {
        return null;
    }
}
