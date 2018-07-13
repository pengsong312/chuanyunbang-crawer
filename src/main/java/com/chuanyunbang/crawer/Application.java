package com.chuanyunbang.crawer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author pengsong
 * @date 20180708
 * @desc 工程启动类
 */
@EnableScheduling
@ImportResource(locations = {"classpath*:spring-context.xml"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//        MangoLogger.useConsoleLogger();
    }
}
