package com.cldbiz.userportal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cldbiz.userportal.dto.WishListDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "WISH_LIST")
@EqualsAndHashCode(callSuper=true)
public @Data class WishList extends AbstractDomain {
	
	@Column
	private String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "WISH_LIST_PRODUCT", 
         joinColumns = {@JoinColumn(name = "WISH_LIST_ID", foreignKey=@ForeignKey(name = "FK_PRODUCT_WISH_LIST"))}, 
         inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", foreignKey=@ForeignKey(name = "FK_WISH_LIST_PRODUCT"))})
	private List<Product> products = new ArrayList<Product>();
	
	public WishList() {
		super();
	}
	
	public WishList(WishListDto wishListDto) {
		super(wishListDto);
		
		this.setName(wishListDto.getName());
	}
}
