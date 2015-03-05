package com.aspappdeveoper.main;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {

	public static void main(String[] args) {
		
		CreateZip CZ = new CreateZip();
		CZ.createZip();
	}
	
	private void createZip() {
		File folder = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("C:\\temp\\ZipFile.zip");
			ZipOutputStream zip = new ZipOutputStream(fos);
			folder = new File("c:\\temp");

			for(File file : folder.listFiles()) {
				byte[] buf = new byte[1024];

				FileInputStream fin = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fin);
				zip.putNextEntry(new ZipEntry(file.getName()));

				int bytesread = 0, bytesBuffered = 0;
				while ((bytesread = bis.read(buf)) > -1) {
					zip.write(buf, 0, bytesread);
					bytesBuffered += bytesread;
					if (bytesBuffered > 1024 * 1024) {
						// flush after 1 mb
						bytesBuffered = 0;
						zip.flush();
					}
				}
				fin.close();
				bis.close();
			}
			zip.closeEntry();
			//don't forget this
			zip.close();
			
			System.out.println("Zip Created");

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
