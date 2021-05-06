package com.cldbiz.afw.common;

import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

public class AfwListPredicates<T> {
	 public List<T> selectEqualsPropertyFromList(List<T> beanList, String propertyName, Object value) {
	    EqualPredicate eqlPredicate = new EqualPredicate(value);
	    BeanPredicate beanPredicate = new BeanPredicate(propertyName, eqlPredicate);
	     
	    return (List<T>) CollectionUtils.select(beanList, beanPredicate);
	  }
}
