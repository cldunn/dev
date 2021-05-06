package com.cldbiz.angularSpring.util;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

	private static TransactionManager transactionManager;
	
	private static UserTransaction userTransaction;
	
	public static void setTransactionManager(TransactionManager transactionManager) {
		AtomikosJtaPlatform.transactionManager = transactionManager;
	}
	
	public static void setUserTransaction(UserTransaction userTransaction) {
		AtomikosJtaPlatform.userTransaction = userTransaction;
	}
	
	@Override
	protected TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return userTransaction;
	}

}
