package com.cldbiz.afw.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.dto.UniqueIdDto;

/* As services are transactional boundaries, */
/* operations not explicitly undone by @AfterClass cleanAfterTests() will persist */
public class UniqueIdServiceTest extends AfwBaseTest {
	private static Timestamp now;
	
	@Resource
	private UniqueIdService uniqueIdService;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		refresh("com/cldbiz/afw/data/UniqueId.xml");
		now = new Timestamp(new Date().getTime());
		
	}

	@AfterClass
	public static void cleanAfterTests() {
		deleteAll("com/cldbiz/afw/data/UniqueId.xml");
	}
	
	@Test
	public void testGetUniqueIdDto() {
		UniqueIdDto uniqueIdDto = uniqueIdService.getUniqueIdBlock("TEST_TABLE_1", new Long(200));
		assertEquals(uniqueIdDto.getNextId().longValue(), 1001L);
		assertEquals(uniqueIdDto.getLastId().longValue(), 1200L);
		
		uniqueIdDto = uniqueIdService.getUniqueIdBlock("TEST_TABLE_1", new Long(200));
		assertEquals(uniqueIdDto.getNextId().longValue(), 1201L);
	}

}
