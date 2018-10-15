package com.mobiquityinc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.mobiquityinc.exception.APIException;

/**
 * Everything which has to do with open a file and close it are in this class
 * @author Allan 10/15/2018
 *
 */
public class FileUtils {
	private String path;

	public FileUtils(String path) {
		this.path = path;
	}

	/**
	 * Responsible to open, read and close pack file.
	 * @return
	 * @throws APIException
	 */
	public List<String> readFile() throws APIException {
		List<String> lines = new ArrayList<>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			String line;
			fileReader = new FileReader(this.path);
			bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}

			return lines;
		} catch (IOException e) {
			throw new APIException(e);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				throw new APIException(e);
			}
		}
	}
}
