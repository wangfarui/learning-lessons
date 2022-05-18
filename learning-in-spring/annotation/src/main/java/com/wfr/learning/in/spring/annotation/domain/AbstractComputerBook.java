package com.wfr.learning.in.spring.annotation.domain;

/**
 * 计算机书籍
 *
 * @author wangfarui
 * @since 2022/5/12
 */
public abstract class AbstractComputerBook implements IBook {

    private String name;

    private String author;

    private String publishingHouse;

    @Override
    public String getBookName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
