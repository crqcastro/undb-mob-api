package br.com.ionicmelhorlinguagem.api.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileHelper {

	public static File getFile(String path) throws IOException {
		
		InputStream iputStream;
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		iputStream = classLoader.getResourceAsStream(path);		

		if (iputStream == null) {
			iputStream = FileHelper.class.getClassLoader().getResourceAsStream(path);
		}

		if (iputStream == null) {
			iputStream = FileHelper.class.getResourceAsStream(path);
		}
		
		File tempFile = File.createTempFile("fatura", "pdf");
		tempFile.deleteOnExit();
		FileOutputStream out = new FileOutputStream(tempFile);
		IOUtils.copy(iputStream, out);
		return tempFile;
		
	}
	
}
