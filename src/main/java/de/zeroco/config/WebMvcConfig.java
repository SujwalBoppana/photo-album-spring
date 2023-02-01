package de.zeroco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import de.zeroco.dao.PhotoDao;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "de.zeroco.controller", "de.zeroco.dao" ,"de.zeroco.service"})
public class WebMvcConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/jsp/");
		vr.setSuffix(".jsp");
		return vr;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean
	public DriverManagerDataSource getDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/zerocode?useSSL=false");
		ds.setUsername("admin");
		ds.setPassword("Sujwal@123");

		return ds;
	}

	@Bean
	public PhotoDao getConnectionObject() {
		return new PhotoDao(getDataSource());
	}
}