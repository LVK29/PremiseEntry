package com.trinity.digitalEntryPass.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class GeSelfScreeningForm {

	public void generateForm(String user) throws DocumentException {
		// Creating PDF document object

		// Saving the document
		try {
			// get form
			// get form
			PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/templates/geSelfScreeningForm.pdf"));
			// Modify file using PdfReader
			// new pdf file name
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("filled_GESelfScreening_Form.pdf"));
			PdfContentByte content = pdfStamper.getOverContent(1);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase("Kartikeyan LV"), 230, 615, 0);

			addTicks(content);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase("Kartikeyan LV<insert sign image>"), 230, 70, 0);

			
			pdfStamper.close();
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PDF created");

	}

	private static void addTicks(PdfContentByte content)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		//String filename=new ClassPathResource("/images/tick.png").getFilename();
		Image image = Image.getInstance("./src/main/resources/images/tick.png");
		image.scaleAbsolute(15, 15);

		// "yes" column positions
		image.setAbsolutePosition(430, 550);
		content.addImage(image);
		// contact person details should be added
		Font fontSettings = new Font(FontFamily.TIMES_ROMAN, 10f);
		Paragraph p1 = new Paragraph("Wuhan :) ", fontSettings);
		ColumnText.showTextAligned(content, Element.ALIGN_LEFT, p1, 430, 530, 0);
		// "no" column positions
		image.setAbsolutePosition(540, 540);
		content.addImage(image);

		// "yes" column positions
		image.setAbsolutePosition(463, 450);
		content.addImage(image);
		
		// "no" column positions
		image.setAbsolutePosition(543, 447);
		content.addImage(image);

		// "yes" column positions symptoms
		image.setAbsolutePosition(463, 390);
		content.addImage(image);
		image.setAbsolutePosition(463, 360);
		content.addImage(image);

		image.setAbsolutePosition(463, 335);
		content.addImage(image);

		image.setAbsolutePosition(463, 310);
		content.addImage(image);

		// "no" column positions
		image.setAbsolutePosition(543, 390);
		content.addImage(image);

		image.setAbsolutePosition(543, 360);
		content.addImage(image);

		image.setAbsolutePosition(543, 335);
		content.addImage(image);

		image.setAbsolutePosition(543, 310);
		content.addImage(image);
		
		// final admit value
		// for yes
		image.setAbsolutePosition(460, 40);
		content.addImage(image);
		// for no
		image.setAbsolutePosition(415, 40);
		content.addImage(image);

	}
}
