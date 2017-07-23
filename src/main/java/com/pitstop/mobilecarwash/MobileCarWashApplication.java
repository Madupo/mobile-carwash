package com.pitstop.mobilecarwash;

import com.pitstop.mobilecarwash.entity.*;
import com.pitstop.mobilecarwash.repository.*;
import com.pitstop.mobilecarwash.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Date;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class MobileCarWashApplication {

	@Bean
	public FilterRegistrationBean corsFilterDev() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner testingDemoDev(UserRepository userRepository, UserService userService, RoleRepository roleRepository, RoleService roleService, ComplexRepository complexRepository, ComplexService complexService, WashTypeRepository washTypeRepository, WashTypeService washTypeService, WashTimeRepository washTimeRepository, WashTimeService washTimeService) {
		return (args) -> {
			Role role = new Role("user");
			Role roleAdmin = new Role("admin");
			roleService.addRole(role);
			roleService.addRole(roleAdmin);

			//// TODO: 2017/06/30 add default complex
			Complex complex = new Complex("First Complex","46 Geranium Street, Birch Acres, Kempton Park",new Date(),new Date());
			complexService.addComplex(complex);

			//// TODO: 2017/07/04 add default wash types
			WashType washType = new WashType("Full Wash",90.00);
			WashType washType1 = new WashType("Interior Wash", 50.00);
			washTypeService.addWashType(washType);
			washTypeService.addWashType(washType1);

			//// TODO: 2017/07/17 add default washes
			WashTime washTime = new WashTime("08:00 to 10:00");
			WashTime washTime1 = new WashTime("10:00 to 11:00");

			washTimeService.addWashTime(washTime);
			washTimeService.addWashTime(washTime1);


			//// TODO: 2017/06/30 add default user
			//adding categories and faculties
			/*User user = new User("Emmie","Madupo",211391901,"emmanuelmadupo@gmail.com","12345",true,role,"0839628990",true);
			System.out.println("User about to be added is " + user);
			userService.addUser(user);*/
		};
	}

	@Bean
	@Profile("QA")
	public CommandLineRunner testingDemoQA(UserRepository userRepository, UserService userService, RoleRepository roleRepository, RoleService roleService, ComplexRepository complexRepository, ComplexService complexService, WashTypeRepository washTypeRepository, WashTypeService washTypeService, WashTimeRepository washTimeRepository, WashTimeService washTimeService) {
		return (args) -> {
			Role role = new Role("user");
			Role roleAdmin = new Role("admin");
			roleService.addRole(role);
			roleService.addRole(roleAdmin);

			//// TODO: 2017/06/30 add default complex
			Complex complex = new Complex("First Complex","46 Geranium Street, Birch Acres, Kempton Park",new Date(),new Date());
			complexService.addComplex(complex);

			//// TODO: 2017/07/04 add default wash types
			WashType washType = new WashType("Full Wash",90.00);
			WashType washType1 = new WashType("Interior Wash", 50.00);
			washTypeService.addWashType(washType);
			washTypeService.addWashType(washType1);

			//// TODO: 2017/07/17 add default washes
			WashTime washTime = new WashTime("08:00 to 10:00");
			WashTime washTime1 = new WashTime("10:00 to 11:00");

			washTimeService.addWashTime(washTime);
			washTimeService.addWashTime(washTime1);


			//// TODO: 2017/06/30 add default user
			//adding categories and faculties
			/*User user = new User("Emmie","Madupo",211391901,"emmanuelmadupo@gmail.com","12345",true,role,"0839628990",true);
			System.out.println("User about to be added is " + user);
			userService.addUser(user);*/
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(MobileCarWashApplication.class, args);
	}
}
