package com.wfr.learning.in.spring.resource;

import com.wfr.learning.in.spring.resource.utls.ResourceUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

/**
 * 基于 {@link org.springframework.core.io.Resource} 资源管理示例
 *
 * @author wangfarui
 * @see Resource
 * @see FileSystemResource
 * @see EncodedResource
 * @since 2022/3/28
 */
public class SpringResourceDemo implements InitializingBean {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    @Override
    public void afterPropertiesSet() throws Exception {
        String content = ResourceUtils.getContent(resource);
        System.out.println(content);
        System.out.println("===================");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("===================");
        System.out.println(userDir);
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringResourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();

        readLocalFile();
    }

    /**
     * 读取文件
     */
    private static void readLocalFile() throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/learning-in-spring/resource/src/main/java/com/wfr/learning/resource/SpringResourceDemo.java";
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
//        String content = ResourceUtils.getContentByInputStream(fileSystemResource.getInputStream());
//        System.out.println(content);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, ResourceUtils.DEFAULT_CHARSET);
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }

}
