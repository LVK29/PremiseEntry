package com.trinity.digitalEntryPass.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.model.AccountInfoModel.VisitorType;
import com.trinity.digitalEntryPass.model.SelfScreeningModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Service
public class GovtFormGenerator {
	
	@Autowired
	AccountInfoMongoRepository accountInfoMongoRepository;
	
	@Autowired
	GeneralUtils generalUtils;
	
	@Autowired
	GeSelfScreeningForm geSelfScreeningForm;
	


	public byte[] generateForm(String date, String user) throws DocumentException {
		// Creating PDF document object

		AccountInfoModel accInfo = accountInfoMongoRepository.findByssoAndSelfScreeningModelDate(user, date);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String fileName = "filled_Govt_Form5_"+user+".pdf";
		// Saving the document
		try {
			
			// get form
			PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/templates/govtForm5.pdf"));
			
			outputStream = generalUtils.getOutputStream(fileName);
			PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
			PdfContentByte content = pdfStamper.getOverContent(1);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getName()), 205, 652, 0);

			// add name
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(generalUtils.getAge(accInfo.getDob())), 205, 632, 0);
			
			// add sso
			ColumnText.showTextAligned(content, Element.ALIGN_LEFT, new Phrase(accInfo.getSso()), 205, 612, 0);

			addTicks(content,accInfo);

			pdfStamper.close();
//			if(accInfo.getUserType().equals(VisitorType.CONTRACTOR))
//			{
//				List<byte[]> combinedData= new ArrayList<byte[]>();
//				combinedData.add(outputStream.toByteArray());
//				combinedData.add(geSelfScreeningForm.generateForm(date, user).toByteArray());
//				return combinePdfBytes(combinedData);
//			}
//			else
//			{
//				return outputStream.toByteArray();
//			}
			return outputStream.toByteArray();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PDF created");
		return outputStream.toByteArray();

	}

	private static void addTicks(PdfContentByte content,AccountInfoModel accInfo)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		Image image = Image.getInstance("./src/main/resources/images/tick.png");
		image.scaleAbsolute(15, 15);
		// "no" column positions
		SelfScreeningModel selfScreen = accInfo.getSelfScreeningModel().get(accInfo.getSelfScreeningModel().size()-1);
		if(selfScreen.getFromContainmentZone())
		{
			image.setAbsolutePosition(390, 447);
			content.addImage(image);
		}
		
		else
		{
			image.setAbsolutePosition(460, 447);
			content.addImage(image);
			
		}
			
			
		if(selfScreen.getIsApthamitraPresent())
		{
			image.setAbsolutePosition(390, 460);
			content.addImage(image);
		}
				
		else
		{
			image.setAbsolutePosition(460, 460);
			content.addImage(image);
			
		}
			
		if(selfScreen.getIsArogaSetuPresent())
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

		if(selfScreen.getSoreThroat())
		{
			image.setAbsolutePosition(390, 505);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 505);
			content.addImage(image);
			
		}
			
		if(selfScreen.getCough())
		{
			image.setAbsolutePosition(390, 520);
			content.addImage(image);
		}
			
		else
		{
			image.setAbsolutePosition(460, 520);
			content.addImage(image);
			
		}
			
		if(selfScreen.getFever())
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
	
	public byte[] combinePdfBytes(List<byte[]> pdfByteList) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document doc = new Document();
		PdfSmartCopy pdfCopy;
		try {
			pdfCopy = new PdfSmartCopy(doc, outputStream);
			doc.open();
			for ( byte[] pdfbyte :pdfByteList) {
				PdfReader reader = new PdfReader(pdfbyte) ;
	            pdfCopy.addDocument(reader);    
	        }
			doc.close();
	        return outputStream.toByteArray();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	    }
	
	
}

