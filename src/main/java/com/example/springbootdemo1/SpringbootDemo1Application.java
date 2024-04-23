package com.example.springbootdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemo1Application.class, args);
	}

}
/*
 * @场景启动器 spring-boot-starter：spring boot的场景启动器
 * 作用：帮助导入web模块正常运行时依赖的组件
 *
 * spring boot将所有的功能场景都抽象出来，做成一系列的starters（启动器）,只需要要在项目中引入，这些starters相关场景的依赖就会导入进来；
 * 需要什么功能，就导入什么场景启动器
 */