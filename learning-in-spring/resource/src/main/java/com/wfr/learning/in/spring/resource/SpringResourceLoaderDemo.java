package com.wfr.learning.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 基于 {@link ResourceLoader} 资源加载示例
 *
 * @author wangfarui
 * @see ResourceLoader
 * @see DefaultResourceLoader
 * @see FileSystemResourceLoader
 * @see EncodedResource
 * @since 2022/3/29
 */
public class SpringResourceLoaderDemo implements ResourceLoaderAware, InitializingBean {

    @Autowired
    private ResourceLoader resourceLoader1;

    private ResourceLoader resourceLoader2;

    @Autowired
    private ApplicationContext resourceLoader3;

    private String location = "classpath:/META-INF/default.properties";

    @Override
    public void afterPropertiesSet() throws Exception {
        // 三个ResourceLoader均为同一个实例变量(AnnotationConfigApplicationContext)
        System.out.println("resourceLoader1 == resourceLoader2 : " + (resourceLoader1 == resourceLoader2));
        System.out.println("resourceLoader1 == resourceLoader3 : " + (resourceLoader1 == resourceLoader3));

        System.out.println("ResourceLoader.classLoader : " + resourceLoader1.getClassLoader());
        System.out.println("ResourceLoader.resource : " + resourceLoader1.getResource(location));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader2 = resourceLoader;
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringResourceLoaderDemo.class);
        applicationContext.refresh();
        applicationContext.close();

        readLocalFile();
    }

    private static void readLocalFile() throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/learning-in-spring/resource/src/main/java/com/wfr/learning/resource/SpringResourceLoaderDemo.java";
        // 新建一个 FileSystemResourceLoader 对象
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        // FileSystemResource => WritableResource => Resource
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 字符输入流
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }

}
