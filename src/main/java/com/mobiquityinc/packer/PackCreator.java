package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.bean.ListPackagesBean;
import com.mobiquityinc.bean.PackageBean;

/**
 * This is the most important class which happens all the creation of the best packages.
 * We are using combinations (same of math) to create all the possibilities and them analyze and create the best pack to send.
 * 
 * @author Allan 10/15/2018
 *
 */
public class PackCreator implements Creator<String, ListPackagesBean>{
	/**
	 * It is a simple caller method which will delegate the TODOs
	 */
	public String create(ListPackagesBean listPack) {
		return stringfyList(generatePacksPayload(listPack.getTotalWeight(), createPossibilities(listPack)));
	}

	/**
	 * Here is where the magic of combination happens, the catch is to use the binary representation 
	 * to generate all possible of combinations, that is the reason of the shift left operations
	 * Note that our return is a list of list since we can have lots of combinations.
	 * @param listPack
	 * @return
	 */
	private List<List<PackageBean>> createPossibilities(ListPackagesBean listPack) {
		List<List<PackageBean>> allPossibilities = new ArrayList<>();
		List<PackageBean> listBean = listPack.getPackages();
		int size = listBean.size();
		for (int i = 0; i < (1 << size); i++) { //Shift Left operation
			List<PackageBean> possibilities = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				if ((i & (1 << j)) != 0) { //Shift Left operation
					possibilities.add(listBean.get(j));
				}
			}
			allPossibilities.add(possibilities);
		}
		return allPossibilities;
	}
	
	/**
	 * After we have all combinations we just need to figure out which is the best to return
	 * @param maxWeight
	 * @param possibilites
	 * @return
	 */
	private List<PackageBean> generatePacksPayload(Integer maxWeight, List<List<PackageBean>> possibilites) {
		List<PackageBean> bestDeal = null;
		double weight = 0.0;
		double price = 0.0;

		for (List<PackageBean> list : possibilites) {
			double interationWeight = 0.0;
			double iterationPrice = 0.0;

			for (int i = 0; i < list.size(); i++) { //Simple for to check get the sum of weights and prices of a combination.
				interationWeight += list.get(i).getWeight();
				iterationPrice += list.get(i).getPrice();
			}

			if (interationWeight <= maxWeight) { //Check the weight and then check the price.
				if (price < iterationPrice) {
					weight = interationWeight;
					price = iterationPrice;
					bestDeal = list;
				}

				if (price == iterationPrice) {
					if (interationWeight < weight) { //If the price is the same, we choose the one which weights less.
						weight = interationWeight;
						bestDeal = list;
					}
				}
			}
		}
		return bestDeal;
	}

	private String stringfyList(List<PackageBean> list) {
		String payload = "";
		if (list == null) {
			return "-";
		}
		for (PackageBean pack : list) {
			payload += pack.toString() + ",";
		}
		return payload.trim().substring(0, payload.trim().length() - 1);
	}
}
