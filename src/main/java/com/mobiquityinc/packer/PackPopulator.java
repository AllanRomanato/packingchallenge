package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.bean.ListPackagesBean;
import com.mobiquityinc.bean.PackageBean;
import com.mobiquityinc.exception.APIException;

import static com.mobiquityinc.util.Constants.MAXWEIGHTPACKS;
import static com.mobiquityinc.util.Constants.MAXPACKSIZE;
import static com.mobiquityinc.util.Constants.MAXCOSTITEM;
import static com.mobiquityinc.util.Constants.MAXWEIGHTITEM;

/**
 * Resposible class to create and populate the beans with the file information which will be treated later
 * @author Allan 10/15/2018
 *
 */
public class PackPopulator implements Creator<List<ListPackagesBean>, List<String>>{
	/**
	 * 	Iterate over a list of several packs and try to populate it. If there is any validation error the execution will stop and a APIException will be thrown	
	 */
	public List<ListPackagesBean> create(List<String> packs) throws APIException {
		List<ListPackagesBean> listPackages = new ArrayList<>();

		for (String pack : packs) {
			listPackages.add(populatePackList(pack));
		}
		return listPackages;
	}

	/**
	 * Populate the pack list with the info retrieved from the file already in String
	 * @param pack
	 * @return
	 * @throws APIException
	 */
	private ListPackagesBean populatePackList(String pack) throws APIException {
		Integer totalWeight;
		List<PackageBean> listPacks = new ArrayList<>();
		String[] primaryToken = pack.split(":");
		totalWeight = Integer.parseInt(primaryToken[0].trim());
		String[] packsToken = primaryToken[1].trim().split(" ");

		//If there is any inconsistence or it doesn't follow the constraints it will drop the APIException showing what might the error be.
		if (primaryToken.length != 2 || totalWeight > MAXWEIGHTPACKS || packsToken.length > MAXPACKSIZE) {
			throw new APIException(
					"Error parsing due to following line \"" + pack + "\"\nTotal weight can't be bigger then "
							+ MAXWEIGHTPACKS + "\nNumber of items can't be bigger then " + MAXPACKSIZE);
		}

		for (int i = 0; i < packsToken.length; i++) {
			PackageBean bean = new PackageBean();
			String[] packValues = packsToken[i].split(",");
			Double weight = Double.parseDouble(packValues[1]);
			Double price = Double.parseDouble(packValues[2].substring(1, packValues[2].length() - 1));

			if (price > MAXCOSTITEM || weight > MAXWEIGHTITEM) {
				throw new APIException(
						"Error parsing due to following line \"" + pack + "\"\nItem weight can't be bigger then "
								+ MAXWEIGHTITEM + "\nItem cost can't be bigger then " + MAXCOSTITEM);
			}

			bean.setIndex(Integer.parseInt(packValues[0].substring(1)));
			bean.setWeight(weight);
			bean.setPrice(price);
			listPacks.add(bean);
		}
		return new ListPackagesBean(totalWeight, listPacks);
	}
}
