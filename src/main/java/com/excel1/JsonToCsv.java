package com.excel1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import org.apache.commons.io.FileUtils;
//import org.json.CDL;
import org.json.JSONArray;
import org.junit.Test;

public class JsonToCsv {

	private StringBuilder sb = new StringBuilder();

	@Test
	public void jsontocsv() throws Exception {
		System.out.println("jsontocsv started");
		String fileNameIn = "E:/files/incsv.csv";
		String fileNameOut = "E:/files/outcsv.csv";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileNameIn));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			JSONArray array = new JSONArray(
			CDL.toJSONArray(sb.toString()).toString());
			String csv = CDL.toString(array);
			FileUtils.write(new File(fileNameOut), csv, "UTF-8");
//			FileOutputStream outputStream = new FileOutputStream(fileNameOut);
//		    byte[] strToBytes = csv.getBytes();
//		    outputStream.write(strToBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
}