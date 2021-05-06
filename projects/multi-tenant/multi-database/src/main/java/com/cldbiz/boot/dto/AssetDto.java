package com.cldbiz.boot.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.Asset;

public class AssetDto extends BaseDto {
	private static final long serialVersionUID = -4248933289927418274L;

	@Size(max=40)
	private String type;
	
	@Size(max=80)
	private String keyName;
	
	private Long productId;
	
	private boolean active;

	private List<AssetDto> children = new ArrayList<AssetDto>();
	
	public AssetDto() {}
	
	public AssetDto(Asset asset) {
		AssetDto.asAssetDto(this, asset);
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

	public List<AssetDto> getChildren() {
		return children;
	}

	public void setChildren(List<AssetDto> children) {
		this.children = children;
	}

	public static AssetDto asAssetDto(AssetDto assetDto, Asset asset) {
		if (assetDto == null) {
			assetDto = new AssetDto();
		}
		
		if (asset == null) {
			assetDto = null;
		}
		else {
			assetDto.setId(asset.getId());
			assetDto.setType(asset.getType());
			assetDto.setKeyName(asset.getKeyName());
			assetDto.setProductId(asset.getProductId());
			assetDto.setActive(asset.isActive());
		}

		return assetDto;
	}

}
