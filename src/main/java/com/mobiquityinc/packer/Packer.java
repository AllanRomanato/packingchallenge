package com.mobiquityinc.packer;

import java.util.List;

import com.mobiquityinc.bean.ListPackagesBean;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.util.FileUtils;

/**
 * Responsible class to start the application
 * @author Allan  10/15/2018
 * 
 *
 */
public class Packer {
	/**
	 * Just an static method to start the app and delegate the directions.
	 * @param file
	 * @return
	 * @throws APIException
	 */
	public static String pack(String file) throws APIException {
		StringBuffer buffer = new StringBuffer();
		List<String> fileContent = new FileUtils(file).readFile();
		PackPopulator helper = new PackPopulator();
		List<ListPackagesBean> populatedItems = helper.create(fileContent);
		PackCreator creator = new PackCreator();
		
		for(ListPackagesBean listPack : populatedItems) {
			buffer.append(creator.create(listPack));
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
