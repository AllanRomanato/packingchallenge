package com.mobiquityinc.bean;

import java.util.List;
/**
 * Class which contais the information of a full set of packs.
 * @author Allan 10/15/2018
 *
 */
public class ListPackagesBean {
	private Integer totalWeight;
	private List<PackageBean> packages;
	
	public ListPackagesBean(Integer totalWeight, List<PackageBean> packages) {
		this.totalWeight = totalWeight;
		this.packages = packages;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public List<PackageBean> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageBean> packages) {
		this.packages = packages;
	}
}
