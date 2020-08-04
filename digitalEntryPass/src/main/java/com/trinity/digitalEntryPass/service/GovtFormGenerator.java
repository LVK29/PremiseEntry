package com.trinity.digitalEntryPass.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class GovtFormGenerator {

	public void generateForm() throws DocumentException {
		// Creating PDF document object

		// Saving the document
		try {
			// get form
			PdfReader pdfReader = new PdfReader("../cabge/src/main/resources/templates/govtForm5.pdf");
			// Modify file using PdfReader
			// new pdf file name
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("filled_Govt_Form5.pdf"));
			PdfContentByte content = pdfStamper.getOverContent(1);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase("Kartikeyan LV"), 205, 652, 0);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase("25"), 205, 632, 0);
			
			// add sso
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase("212668393"), 205, 612, 0);

			addTicks(content);

			pdfStamper.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PDF created");

	}

	private static void addTicks(PdfContentByte content)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		String filename = "tick.png";
		Image image = Image.getInstance(filename);
		image.scaleAbsolute(15, 15);
		// "no" column positions
		image.setAbsolutePosition(460, 447);
		content.addImage(image);
		image.setAbsolutePosition(460, 460);
		content.addImage(image);
		image.setAbsolutePosition(460, 475);
		content.addImage(image);
		image.setAbsolutePosition(460, 490);
		content.addImage(image);
		image.setAbsolutePosition(460, 505);
		content.addImage(image);
		image.setAbsolutePosition(460, 520);
		content.addImage(image);
		image.setAbsolutePosition(460, 535);
		content.addImage(image);

		// "yes" column positions
		image.setAbsolutePosition(390, 447);
		content.addImage(image);
		image.setAbsolutePosition(390, 460);
		content.addImage(image);
		image.setAbsolutePosition(390, 475);
		content.addImage(image);
		image.setAbsolutePosition(390, 490);
		content.addImage(image);
		image.setAbsolutePosition(390, 505);
		content.addImage(image);
		image.setAbsolutePosition(390, 520);
		content.addImage(image);
		image.setAbsolutePosition(390, 535);
		content.addImage(image);
	}
}
