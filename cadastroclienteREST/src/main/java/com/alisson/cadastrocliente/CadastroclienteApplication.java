package com.alisson.cadastrocliente;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CadastroclienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroclienteApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Configuration
	public static class MvcConfig implements WebMvcConfigurer{

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/clientes");
			WebMvcConfigurer.super.addViewControllers(registry);
		}
				
	}

}
