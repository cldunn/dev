package com.cldbiz.afw.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.domain.UniqueId;

public class UniqueIdDaoTest extends AfwBaseTest {
	private static Timestamp now;
	
	@Resource
	private UniqueIdDao uniqueDao;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		refresh("com/cldbiz/afw/data/uniqueid.xml");
		now = new Timestamp(new Date().getTime());
		
	}

	@AfterClass
	public static void cleanAfterTests() {
		deleteAll("com/cldbiz/afw/data/uniqueid.xml");
	}

	@Test
	public void testGetExistingUniqueId() {
		UniqueId uniqueId = uniqueDao.findUniqueId("EMPLOYEE");
		assertEquals(uniqueId.getNextId().longValue(), 1001L);
	}

	@Test
	public void testGetNonExistingUniqueId() {
		UniqueId uniqueId = uniqueDao.findUniqueId("Test_Table_2");
		assertEquals(uniqueId.getNextId().longValue(), 1001L);
	}
}
