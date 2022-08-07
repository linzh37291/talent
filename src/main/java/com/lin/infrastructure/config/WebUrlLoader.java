package com.lin.infrastructure.config;

//import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 获取所有的请求url
 *
 * @author linzihao
 */
@Slf4j
@Component
public class WebUrlLoader implements ApplicationContextAware {

    @Value("${server.servlet.context-path}")
    private String serverContextPath;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("UrlLoader 加載成功！！！");
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);
        for (String beanName : beans.keySet()) {
            //System.err.println(beanName);
            Object value = applicationContext.getBean(beanName);
            if (value == null) {
                continue;
            }
            String path = "";
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(value.getClass(), RequestMapping.class);
            if (requestMapping != null) {
                path = requestMapping.value()[0];
                log.info("path: " + path);
            }
            Method[] methods = value.getClass().getMethods();
            for (Method method : methods) {
                //每个方法必定含有下面的注解中的其中一个
                //  ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
                String url = "";
                String desc = "";
//                if (apiOperation != null) {
//                    desc = apiOperation.value();
//                }
                RequestMapping mapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
                GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
                PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
                DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
                if (postMapping != null) {
                    url = path + postMapping.value()[0];
                } else if (getMapping != null) {
                    url = path + getMapping.value()[0];
                } else if (putMapping != null) {
                    url = path + putMapping.value()[0];
                } else if (deleteMapping != null) {
                    url = path + deleteMapping.value()[0];
                } else if (mapping != null) {  //mapping 顺序一定要在后面
                    url = path + mapping.value()[0];
                } else {
                    continue;
                }
                //log.info("url : {}  , desc : {}, system_code:{}", url, desc, system_code);
                //log.info("url : {}  , desc : {}", url, desc);

                String serverUrl = "";
                try {
                    serverUrl = InetAddress.getLocalHost().getHostAddress()
                            + ":"
                            + serverPort
                            + serverContextPath
                            + url;
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                log.info("请求链接地址---->{}", serverUrl);
                //url信息入库


            }

        }

    }


}