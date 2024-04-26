package com.example.springbootdemo1.bean;

import com.example.springbootdemo1.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
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

/*
 * @一、读取配置文件的变量两种方法
 * 方式一：@ConfigurationProperties注解
 *
 * 方式二：@Value注解
 * <bean class="Person">
 *    <property name="lastName" value="值可以是 字面量/${key}从环境变量、或者配置文件中获取值的/#{SpEL}，这是spring的表达式语言"></property>
 * </bean>
 *
 * @Value注解中也就支持这几种格式的值
 * @Value(${person})
 *
 *                                                 @ConfigurationProperties                                                     @Value
 * 功能                                                 批量注入数据属性                                                 只能一个一个的指定
 * 松散语法绑定（relaxed Binding）                 【支持】(配置文件中的last-name会解析成驼峰命名的方式，lastName)          【不支持】（必须和配置文件中的名称相同，否则代码报错）
 * SpEL,spring表达式语言方面                              【不支持】                                                           【支持】(例如：application.properties文件中age值，修改为#{11*2)
 * JSR303数据校验                                         【支持】                                                             【不支持】
 * 复杂类型封装（比如对象、Map、List等，复杂类型对应基本类型） 【支持】                                                             【不支持】
 *
 * @二、ConfigurationProperties注解和@Value注解的使用场景
 *
 * 1.配置文件是properties还是yml文件，都能获取到值
 * 2.如果我们只是要获取配置文件中的某项值(值要求时是简单类型，复杂类型@Value注解也不能获取)，我们就使用@Value
 * 3.如果我们编写了一个javabean来和配置文件中的值进行映射，就直接使用@ConfigurationProperties就好了
 * 4.@ConfigurationProperties默认是 从【全局配置文件】中获取值
 *
 *
 * 三、@SourceProperty注解，用于从指定路径的文件中，获取值
 * 1.注意：单独使用@PropertySource()注解，拿到的person.properties中的person所有对象值为null
 * 此时，仍然需要@ConfigurationProperties这个注解
 * 2.可以理解为，@PropertySource()指定的文件路径，仅仅是为了将@ConfigurationProperties这个注解中的搜索范围 从【默认配置文件】变成了【默认配置文件+指定路径文件】
 *
 * 四、注解@ImportResource
 * 作用：导入Spring的配置文件，让文件里的内容生效
 * 实现功能：给容器里加组件
 *
 * 之前的写法：
 * 4.1.在/resources下创建bean.xml文件
 * 4.2.创建一个类 service/HelloService.java,不加任何的注解
 * 4.3.实例化ioc容器对象ioc，并另外写一个测试方法，testHelloService()
 *  4.3.1 没有添加@ImportResource注解，使得Spring Boot的配置文件beans.xml生效，b的值打印false;表示Spring Boot里还没有这个配置文件
 *  4.3.2 需要将这个注解@ImportResource，加在一个配置类上；我们就将它加在主配置类上（com.example.springbootdemo1.SpringbootDemo1Application）
 *  4.3.3 在主配置类上，添加@ImportResources注解以后，重新运行观察该方法的返回值变成了true
 *
 * spring boot推荐写法：（不写xml文件，使用配置类来实现）
 * 4.1 创建一个配置类 config.MyAppConfig，加上注解@Configuration，指明当前类是一个配置类（替代之前的Spring配置文件的）
 * 4.1.1 配置类=======beans.xml(测试这种方式时，注释掉主配置中的@ImportResources注解，此时beans.xml失效)
 * 4.1.2 定义个方法helloService(),并添加注解@Bean,方法的返回值是HelloService()
 * 伪代码：
 *
 *
 * @Configuration
 * public class MyAppConfig {
 * @Bean
 * public HelloService helloService(){
 *   return new HelloService();
 * }
 *
 * 五、配置文件占位符
 *
 *
 *
 */
@Component
// @Validated
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:person.properties")
@ImportResource
// 一、获取默认配置文件（默认文件是，名称为application.properties或者application.yml）中的变量值
// 1.1 使用注解@ConfigurationProperties
// 1.2 使用@Value()注解
// 二、获取指定路径的文件中的值,使用注解@PropertySource()

public class Person {
	// ${key}方式,读取配置文件中person.lastName的值
	// @Email
	// @Value("${person.lastName}")

	private String lastName;
	// SpEL，spring表达式方式，直接为person.age赋值
	// @Value("#{11*2}")
	private Integer age;
	// 字面量方式，直接赋值false，直接为person.boss赋值
	// @Value("false")
	private Boolean boss;
	private Date birth;
	// 使用@Value注解，来拿Map对象的值，报错：Could not resolve placeholder 'person.maps' in value "${person.maps}"
	/**
	 * 这涉及到两种方式的使用场景
	 * <p>
	 * 注意：@Value注解的方式，来拿配置文件中的值，是不支持复杂封装的
	 */
	// @Value("${person.maps}")
	private Map<Object, String> maps;
	// 报错：Could not resolve placeholder 'person.list' in value "${person.list}"
	// @Value("${list}")
	private List<Object> list;
	// 报错：Could not resolve placeholder 'person.dog' in value "${person.dog}"
	// @Value("${person.dog}")
	private Dog dog;

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setMaps(Map<Object, String> maps) {
		this.maps = maps;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public Boolean getBoss() {
		return boss;
	}

	public Date getBirth() {
		return birth;
	}

	public Map<Object, String> getMaps() {
		return maps;
	}

	public List<Object> getList() {
		return list;
	}

	public Dog getDog() {
		return dog;
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
