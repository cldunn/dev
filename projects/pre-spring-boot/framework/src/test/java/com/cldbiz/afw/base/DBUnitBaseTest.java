package com.cldbiz.afw.base;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.IColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBUnitBaseTest {
	private static final Logger log = LoggerFactory.getLogger(DBUnitBaseTest.class);
	
	protected static Properties testProperties;
	protected static IDatabaseConnection iDatabaseConnection;
	
	static {
		testProperties = new Properties();
		try {
			testProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));
			testProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties"));
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}
	
	@BeforeClass
	public static void setup() throws ClassNotFoundException, SQLException, DatabaseUnitException {
		String[] tableTypes = new String[] {
			"TABLE", "VIEW"
		};
		
		iDatabaseConnection = getConnection();
		iDatabaseConnection.getConfig().setProperty("http://www.dbunit.org/properties/tableType", tableTypes);
		iDatabaseConnection.getConfig().setProperty("http://www.dbunit.org/features/qualifiedTableNames", true);
		
		try {
			Class dataTypeFactoryClass = Class.forName(testProperties.getProperty("dbunit.datatypeFactory"));
			iDatabaseConnection.getConfig().setProperty("http://www.dbunit.org/properties/datatypeFactory", dataTypeFactoryClass.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@AfterClass
	public static void cleanUp() throws SQLException {
		iDatabaseConnection.close();
	}
	
	protected static void cleanInsert(String xmlFile) {
		try {
			IDataSet iDataSet = getDataSet(xmlFile);
			DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, iDataSet);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	protected static void refresh(String xmlFile) {
		try {
			IDataSet iDataSet = getDataSet(xmlFile);
			DatabaseOperation.REFRESH.execute(iDatabaseConnection, iDataSet);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	protected static void deleteAll(String xmlFile) {
		try {
			IDataSet iDataSet = getDataSet(xmlFile);
			DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, iDataSet);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	protected static void delete(String xmlFile) {
		try {
			IDataSet iDataSet = getDataSet(xmlFile);
			DatabaseOperation.DELETE.execute(iDatabaseConnection, iDataSet);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	private static IDatabaseConnection getConnection() throws ClassNotFoundException, SQLException, DatabaseUnitException {
		DatabaseConnection dbConn = null;
		Class.forName(testProperties.getProperty("ds.test.driverClassName"));
		try {
			dbConn = new DatabaseConnection(DriverManager.getConnection(
					testProperties.getProperty("ds.test.url"),
					testProperties.getProperty("ds.test.username"),
					testProperties.getProperty("ds.test.password")
				));
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return dbConn;
	}
	
	private static IDataSet getDataSet(String xmlFile) throws DataSetException {
		InputStream dataSet = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlFile);
		
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		
		IDataSet iDataSet = builder.build(dataSet);

		return iDataSet;
		
	}
}
