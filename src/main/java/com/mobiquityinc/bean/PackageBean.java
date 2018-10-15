package com.mobiquityinc.bean;

/**
 * Class which contains each pack information
 * @author Allan 10/15/2018
 *
 */
public class PackageBean {
	private Integer index;
	private Double weight;
	private Double price;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.valueOf(index);
	}
}
