package com.upa.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.upa.web.beans.MyBean;
import com.upa.web.service.EmployeeManager;
import com.upa.web.service.HandScanService;
import com.upa.web.service.HandScanServiceImpl;
import com.upa.web.service.UserService;
import com.upa.web.service.UserServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.upa.web")
@Import(value = {HibernateConfig.class, ApplicationProperties.class})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name="myBean")
	@Scope("singleton")
	public MyBean getMyBean(){ return new MyBean();}


	@Bean
	public EmployeeManager getEmployeeManager(){
		return new EmployeeManager();
	}
	
	@Bean(name="userservice")
	public UserService userService(){
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		return userServiceImpl;
	}
	
	@Bean(name="handScanService")
	public HandScanService handScanService(){
		HandScanServiceImpl handscanserviceimpl = new HandScanServiceImpl();
		return handscanserviceimpl;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	 }
}