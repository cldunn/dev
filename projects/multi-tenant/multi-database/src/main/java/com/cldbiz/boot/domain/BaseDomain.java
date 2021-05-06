package com.cldbiz.boot.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

@MappedSuperclass
public abstract class BaseDomain implements Serializable {
	
	@Transient
	private String uid = java.util.UUID.randomUUID().toString();
	
	private String getUid() {
		return uid;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this.getClass());
	};
	
    public int hashCode() {
        if (uid != null) {
            return uid.hashCode();
        } else {
            return super.hashCode();
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BaseDomain)) {
            return false;
        }

        BaseDomain other = (BaseDomain)o;
        return uid.equals(other.getUid());
    }

}
