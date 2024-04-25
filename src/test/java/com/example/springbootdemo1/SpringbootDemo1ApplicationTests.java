package com.example.springbootdemo1;

import com.example.springbootdemo1.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 *@SpringBootTest
 * 可以在测试期间，类似编码一样，自动注入到容器的功能
 *
 * @RunWith(SpringRunner.class)
 * 单元测试使用Spring的驱动器来跑，而不使用JUnit
 *
 * @AutoWired wired adj.连线的，联网的，兴奋不安的
 * v.wire的过去式，使……连线，给……接通电源
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDemo1ApplicationTests {
	@Autowired
	Person person;

	@Test
	void contextLoads() {
		// 输出一下，要测试的person对象
		System.out.println(person);
	}

}
