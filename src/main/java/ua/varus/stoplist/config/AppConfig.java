package ua.varus.stoplist.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpSessionListener;


@EnableWebMvc
@Configuration
@ComponentScan({ "ua.varus.*" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(10000000);
		return resolver;
	}
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
		registry.addResourceHandler("/admin/resources/**").addResourceLocations("resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		super.configureDefaultServletHandling(configurer);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
	}


//	@Bean
//	public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
//		return new ServletListenerRegistrationBean<>(new MySessionListener());
//	}
}
