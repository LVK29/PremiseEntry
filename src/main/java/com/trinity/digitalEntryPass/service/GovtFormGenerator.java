package com.trinity.digitalEntryPass.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Service
public class GovtFormGenerator {
	
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;

	public void generateForm(String user) throws DocumentException {
		// Creating PDF document object

		AccountInfoModel accInfo = accountInfoMongoRepository.findBysso(user);
		// Saving the document
		try {
			// get form
			PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/templates/govtForm5.pdf"));
			// Modify file using PdfReader
			// new pdf file name
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("filled_Govt_Form5.pdf"));
			PdfContentByte content = pdfStamper.getOverContent(1);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getName()), 205, 652, 0);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(getAge(accInfo.getDob())), 205, 632, 0);
			
			// add sso
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getSso()), 205, 612, 0);

			addTicks(content,accInfo);

			pdfStamper.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PDF created");

	}

	private static void addTicks(PdfContentByte content,AccountInfoModel accInfo)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		Image image = Image.getInstance("./src/main/resources/images/tick.png");
		image.scaleAbsolute(15, 15);
		// "no" column positions
		SelfScreeningModel selfScreen = accInfo.getSelfScreeningModel().get(accInfo.getSelfScreeningModel().size()-1);
		if(selfScreen.getFever())
		{
			image.setAbsolutePosition(390, 447);
			content.addImage(image);
		}
		
		else
		{
			image.setAbsolutePosition(460, 447);
			content.addImage(image);
			
		}
			
			
		if(selfScreen.getCough())
		{
			image.setAbsolutePosition(390, 460);
			content.addImage(image);
		}
				
		else
		{
			image.setAbsolutePosition(460, 460);
			content.addImage(image);
			
		}
			
		if(selfScreen.getSoreThroat())
		{
			image.setAbsolutePosition(390, 475);
			content.addImage(image);	
		}
				
		else
		{
			image.setAbsolutePosition(460, 475);
			content.addImage(image);
			
		}
			
		if(selfScreen.getBreathingDifficulty())
		{
			image.setAbsolutePosition(390, 490);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 490);
			content.addImage(image);
			
		}

		if(selfScreen.getIsArogaSetuPresent())
		{
			image.setAbsolutePosition(390, 505);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 505);
			content.addImage(image);
			
		}
			
		if(selfScreen.getIsApthamitraPresent())
		{
			image.setAbsolutePosition(390, 520);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 520);
			content.addImage(image);
			
		}
			
		if(selfScreen.getFromContainmentZone())
		{
			image.setAbsolutePosition(390, 535);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 535);
			content.addImage(image);
		}

		
	}
	
	public String getAge(String dob)
	{
		LocalDate today = LocalDate.now(); 
		int year=Integer.parseInt(dob.split("/")[2]);
		int month=Integer.parseInt(dob.split("/")[1]);
		int day=Integer.parseInt(dob.split("/")[0]);
		
		LocalDate birthDate = LocalDate.of(year,month,day);
		Period p = Period.between(birthDate, today);
		return Integer.toString(p.getYears());
		
	}
}
