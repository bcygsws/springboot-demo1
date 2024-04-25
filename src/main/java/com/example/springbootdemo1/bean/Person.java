package com.example.springbootdemo1.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 *
 * @那么如何将resources下的yaml配置文件中定义的变量person映射到当前文件中？
 * 解决：
 * 使用一个注解，@ConfigurationProperties
 * @ConfigurationProperties 会告诉Spring Boot将本类中的属性和配置文件中的相关进行绑定
 * 其中第一个参数prefix表示，只获取yaml配置文件中定义的person对象的属性进行一一映射
 * prefix="person"
 *
 * 注意：读取yaml文件时，@ConfigurationProperties注解的类中必须有getter/setter方法，否则在test中测试
 * 也是拿不到数据的，所有变量拿到的值都是null
 * 问题解决，参考文档：https://blog.csdn.net/eagle_321/article/details/119039386
 *
 * @Component注解： 将Person类加入到容器中
 * 关于@Component注解的作用
 * 参考：https://blog.csdn.net/wenhuakulv2008/article/details/132577138
 *
 *
 */

@Component
@ConfigurationProperties(prefix = "person")

public class Person {
	private String lastName;
	private Integer age;
	private Boolean boss;
	private Date birth;
	private Map<Object, String> maps;
	private List<Object> list;
	private Dog dog;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getBoss() {
		return boss;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Map<Object, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<Object, String> maps) {
		this.maps = maps;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Person{" +
				"lastName='" + lastName + '\'' +
				", age=" + age +
				", boss=" + boss +
				", birth=" + birth +
				", maps=" + maps +
				", list=" + list +
				", dog=" + dog +
				'}';
	}
}
