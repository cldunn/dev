package com.cldbiz.afw.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.cldbiz.afw.config.AfwExecutionContext;
import com.cldbiz.afw.dto.UserInfoDto;

@MappedSuperclass
public class EntityDomain extends BaseDomain {

    @Id 
    @Column(name="ID", nullable=false)
    @GeneratedValue(generator="afwIndentifierGenerator") 
    @GenericGenerator(strategy="com.cldbiz.afw.util.AfwIdentifierGenerator", name="afwIndentifierGenerator" ) 
	private Long id;
    
    @Version
    @Column(name="VERSION", nullable=false)
	private Long version;
	
    @Column(name="CREATED_BY", nullable=false)
    private String createBy;

    @Column(name="CREATED_DATE", nullable=false)
    private Timestamp createDate;
    
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    
    @Column(name="MODIFIED_DATE")
    private Timestamp modifiedDate;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@PrePersist
	public void insertCreatedByAndCreatedDate() {
		UserInfoDto userInfoDto = AfwExecutionContext.getUserInfoDto();
		
		this.setCreateBy(userInfoDto.getName());
		this.setCreateDate(new Timestamp(new Date().getTime()));
	}
	
	@PreUpdate
	public void updateModifiedByAndModifiedDate() {
		UserInfoDto userInfoDto = AfwExecutionContext.getUserInfoDto();
		
		this.setModifiedBy(userInfoDto.getName());
		this.setModifiedDate(new Timestamp(new Date().getTime()));
	}
}
