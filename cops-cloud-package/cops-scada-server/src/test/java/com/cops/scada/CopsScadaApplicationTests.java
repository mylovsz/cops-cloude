package com.cops.scada;

import com.cops.scada.service.MemaryCacheService;
import com.cops.scada.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopsScadaApplicationTests {

	@Autowired
	MemaryCacheService memaryCacheService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CopsScadaApplicationTests.class);
	@Test
	public void contextLoads() {
		LOGGER.info("test");
	}

	@Test
	public void testMemaryCache(){
		memaryCacheService.sSetAndTime("测试数据", 3600, new String[]{"H1912345000000", "H1912345000001"});
		long a = 0L;
		a = memaryCacheService.sSetAndTime("测试数据", 3600, new String[]{"H1912345000002", "H1912345000003"});
		System.out.println("1. 成功条数："+a);

		Date d = new Date();
		long timeout = DateUtil.getEndTime(d).getTime() - d.getTime();
		a = memaryCacheService.sSetAndTime("测试数据", timeout, "H1912345000000");
		System.out.println("2. 超时时间："+timeout+", 成功条数："+a);

		boolean isExist = memaryCacheService.sHasKey("测试数据", "H1912345000000");
		System.out.println("3. H1912345000000："+(isExist?"存在":"不存在"));
	}

}
