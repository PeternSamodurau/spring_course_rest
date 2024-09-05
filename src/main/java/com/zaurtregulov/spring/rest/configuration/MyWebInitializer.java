package com.zaurtregulov.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//MyWebInitializer` является точкой входа для вашего Spring приложения.
//Tomcat знает что MyWebInitializer наследует AbstractAnnotationConfigDispatcherServletInitializer - инициализирует деспетчер сервлетов.,
// который имеет аннотацию @WebAppConfiguration,
// которая указывает что MyWebInitializer является конфигурационным классом
// и что он должен быть использован для инициализации приложения.

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // возвращает класс MyConfig, который является конфигурационным классом для корневого контекста приложения.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }
    //возвращает сопоставление сервлета для приложения, которое указывает, что сервлет будет обрабатывать все запросы к корневому URL ("/").
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
