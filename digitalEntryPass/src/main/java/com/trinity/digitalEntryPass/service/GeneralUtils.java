package com.trinity.digitalEntryPass.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;

@Component
public class GeneralUtils {

	final static String QR_SECRET_KEY = "NoobMaster69";
	public static String[] FLOOR_CODE_VALUES = { "GFL_ENTRY", "GFR_ENTRY ", "GFL_EXIT ", "GFR_EXIT", "1F_ENTRY",
			"2F_EXIT", "3F_EXIT", "4F_EXIT", "5F_EXIT", "6F_EXIT", "7F_EXIT", "1F_ENTRY", "2F_ENTRY", "3F_ENTRY",
			"4F_ENTRY", "5F_ENTRY", "6F_ENTRY", "7F_ENTRY" };

	public String convertTimeToStringData() {
		Date date = Calendar.getInstance().getTime();

		// Display a date in day, month, year format
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String today = formatter.format(date);
		System.out.println("Today : " + today);
		return today;
	}

	public String getAge(String dob) {
		LocalDate today = LocalDate.now();
		int year = Integer.parseInt(dob.split("/")[2]);
		int month = Integer.parseInt(dob.split("/")[1]);
		int day = Integer.parseInt(dob.split("/")[0]);

		LocalDate birthDate = LocalDate.of(year, month, day);
		Period p = Period.between(birthDate, today);
		return Integer.toString(p.getYears());

	}

	public ByteArrayOutputStream getOutputStream(String fileName) throws IOException {
		File tempFile;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			tempFile = File.createTempFile(fileName, ".tmp");
			byte[] buffer = new byte[4096];
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tempFile.getAbsolutePath()));
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int bytes = 0;
			while ((bytes = bis.read(buffer, 0, buffer.length)) > 0) {
				outputStream.write(buffer, 0, bytes);
			}
			byteArrayOutputStream.writeTo(outputStream);
			outputStream.close();
			bis.close();
			return byteArrayOutputStream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	public String dateFormatter(String date) {
		return date.replace("-", "/");
	}

}
