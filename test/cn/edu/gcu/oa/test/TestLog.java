package cn.edu.gcu.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	Log log = LogFactory.getLog(this.getClass());
	
	@Test
	public void test(){
		log.debug("测试信息");
		log.error("错误信息");
		log.info("info信息");
	}
}
