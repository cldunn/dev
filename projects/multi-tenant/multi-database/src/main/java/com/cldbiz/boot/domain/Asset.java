package com.cldbiz.boot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cldbiz.boot.dto.AssetDto;

@Entity
@Table(name="ASSET")
public class Asset extends EntityDomain {
	private static final long serialVersionUID = -117950850140660548L;

	@Column(name="TYPE", nullable=false)
	private String type;
	
	@Column(name="KEY_NAME", nullable=false)
	private String keyName;
	
	@Column(name="PRODUCT_ID", nullable=false)
	private Long productId;
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active;
	
 	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "PARENT_ASSET_ID", referencedColumnName = "ID", nullable=true, foreignKey = @ForeignKey(name = "FK_PARENT_ASSET_ID"))
	private List<Asset> children = new ArrayList<Asset>();

    public Asset() {}
	
	public Asset(AssetDto assetDto) {
		Asset.asAsset(this, assetDto);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Asset> getChildren() {
		return children;
	}

	public void setChildren(List<Asset> children) {
		this.children = children;
	}
 	
	public static Asset asAsset(Asset asset, AssetDto assetDto) {
		if (asset == null) {
			asset = new Asset();
		}
		
		if (assetDto == null) {
			asset = null;
		}
		else {
			asset.setId(assetDto.getId());
			asset.setType(assetDto.getType());
			asset.setKeyName(assetDto.getKeyName());
			asset.setProductId(assetDto.getProductId());
			asset.setActive(assetDto.isActive());
		}
		
		return asset;
	}
	
}
