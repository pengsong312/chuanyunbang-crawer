package com.chuanyunbang.crawer;

import com.chuanyunbang.crawer.service.LoadFileDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@EnableScheduling
@ImportResource(locations = {"classpath*:spring-context.xml"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
public class ApplicationTests {

	@Autowired
	private LoadFileDataService loadFileDataService;

	@Test
	public void contextLoads() {
		System.out.println("test");
        assertTrue(1 == 1);
//		loadFileDataService.loadData("/Users/luffy/chuanyunbang/weixin/", "20180623_group_msg.log");
	}

}
