package com.cldbiz.afw.util;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.afw.base.AfwBaseTest;

public class UniqueIdUtilTest extends AfwBaseTest {
	private static Timestamp now;
	
	@Autowired
	UniqueIdUtil uniqueIdUtil;
	
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
	public void testNextId() {
		
		Long nextId = uniqueIdUtil.getNextId("TEST_TABLE_1");
		assertEquals(nextId.longValue(), 1001L);
		
		for (int id = 0; id < 100; id++) {
			nextId = uniqueIdUtil.getNextId("TEST_TABLE_1");
		}
		assertEquals(nextId.longValue(), 1101L);
	}
	
	@Test
	public void testResetBlock() {
		
		// initializes new block
		uniqueIdUtil.resetBlock("TEST_TABLE_1", 100L);
		Long startId = uniqueIdUtil.getNextId("TEST_TABLE_1");
		
		// Get 2 new blocks
		uniqueIdUtil.resetBlock("TEST_TABLE_1", 150L);
		uniqueIdUtil.resetBlock("TEST_TABLE_1", 300L);
		
		// expectedId should be startId plus its block size plus the and second block size
		Long expectedId = startId + 100L + 150L; 
		Long nextId = uniqueIdUtil.getNextId("TEST_TABLE_1");
		
		assertEquals(nextId, expectedId);
	}
}
