package com.trinity.digitalEntryPass.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
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
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Service
public class GeSelfScreeningForm {
	
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;
	
	@Autowired
	GeneralUtils generalUtils;

	public ByteArrayOutputStream generateForm(String date, String user) throws DocumentException {
		// Creating PDF document object
		String fileName = "filled_GESelfScreening_Form_"+user+".pdf";
		AccountInfoModel accInfo = accountInfoMongoRepository.findByssoAndSelfScreeningModelDate(user, date);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// Saving the document
		try {
			// get form
			PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/templates/geSelfScreeningForm.pdf"));
			// Modify file using PdfReader
			// new pdf file name
			outputStream = generalUtils.getOutputStream(fileName);
			PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
			PdfContentByte content = pdfStamper.getOverContent(1);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getName()), 230, 615, 0);

			addTicks(content,accInfo);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getName()), 230, 70, 0);

			
			pdfStamper.close();
			return outputStream;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("PDF created");
		return outputStream;

	}

	private static void addTicks(PdfContentByte content, AccountInfoModel accInfo)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		Image image = Image.getInstance("./src/main/resources/images/tick.png");
		image.scaleAbsolute(15, 15);

		SelfScreeningModel selfScreen = accInfo.getSelfScreeningModel().get(accInfo.getSelfScreeningModel().size()-1);

		// "yes" column positions
		if(selfScreen.getVisitedCountries()!=null &&
				!selfScreen.getVisitedCountries().isEmpty())
		{
			image.setAbsolutePosition(430, 550);
			content.addImage(image);
		}
		else
		{
			// "no" column positions
			image.setAbsolutePosition(540, 540);
			content.addImage(image);
		}
		
		// contact person details should be added
		Font fontSettings = new Font(FontFamily.TIMES_ROMAN, 10f);
		Paragraph p1 = new Paragraph(selfScreen.getVisitedCountries().toString(), fontSettings);
		ColumnText.showTextAligned(content, Element.ALIGN_LEFT, p1, 430, 530, 0);
		
		if(selfScreen.getCloseContact())
		{
			// "yes" column positions
			image.setAbsolutePosition(463, 450);
			content.addImage(image);
		}
		else
		{

			// "no" column positions
			image.setAbsolutePosition(543, 447);
			content.addImage(image);
		}
		

		// "yes" column positions symptoms
		
		if(selfScreen.getFever())
		{
			image.setAbsolutePosition(463, 360);
			content.addImage(image);
		}
		else
		{
			image.setAbsolutePosition(543, 360);
			content.addImage(image);
			
		}
		if(selfScreen.getCough())
		{
			image.setAbsolutePosition(463, 335);
			content.addImage(image);
		}
		else
		{
			image.setAbsolutePosition(543, 335);
			content.addImage(image);
		}
		if(selfScreen.getBreathingDifficulty())
		{
			image.setAbsolutePosition(463, 310);
			content.addImage(image);
		}
		else
		{

			image.setAbsolutePosition(543, 310);
			content.addImage(image);
		}
		


//		//yes
//		image.setAbsolutePosition(463, 390);
//		content.addImage(image);
//		// "no" column positions
//
//		image.setAbsolutePosition(543, 390);
//		content.addImage(image);


		// final admit value
		// for yes
//		image.setAbsolutePosition(460, 40);
//		content.addImage(image);
//		// for no
		image.setAbsolutePosition(415, 40);
		content.addImage(image);

	}
}
