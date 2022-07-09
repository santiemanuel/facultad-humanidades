package com.milprogramadores.model;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.Document;

public class PdfCreatorConstancia {

	private Certificado certificado;
	private File file;
	
	public PdfCreatorConstancia(Certificado certificado, File file) {
		this.certificado = certificado;
		this.setFile(file);
	}
	
	public void createPdf() {
		try {
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(getFile()));
			
			Document doc = new Document(pdfDoc);
			
			PdfFont arial = PdfFontFactory.createFont("/fonts/Arial.ttf");
			PdfFont times = PdfFontFactory.createFont("/fonts/Times.ttf");
			
			pdfDoc.addNewPage();
			
			Text title = new Text("UNIVERSIDAD DE JAVA").setFont(arial).setFontSize(10).setBold();
			Paragraph p1 = new Paragraph();
			p1.setTextAlignment(TextAlignment.CENTER);
			p1.setMarginTop(50);
			p1.add(title);
			
			Text subheader = new Text("Facultad de Humanidades").setFont(arial).setFontSize(12);
			Paragraph p2 = new Paragraph();
			p2.setTextAlignment(TextAlignment.CENTER);
			p2.setMarginTop(45);
			p2.add(subheader);
			
			Text formType = new Text("Constancia de Alumno Regular").setFont(times).setFontSize(14).setBold().setUnderline();
			Paragraph p3 = new Paragraph();
			p3.setTextAlignment(TextAlignment.CENTER);
			p3.setMarginTop(45);
			p3.add(formType);
			
			Text body = new Text(certificado.generarAlumnoRegular()).setFont(arial).setFontSize(10);
			Paragraph p4 = new Paragraph();
			p4.setTextAlignment(TextAlignment.JUSTIFIED);
			p4.setMarginTop(25);
			p4.add(body);
			
			doc.add(p1);
			doc.add(p2);
			doc.add(p3);
			doc.add(p4);
			
			drawBorder(pdfDoc);
			
			doc.close();
			pdfDoc.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawBorder(PdfDocument pdfDoc) {
		float width = pdfDoc.getDefaultPageSize().getWidth();
		float height = pdfDoc.getDefaultPageSize().getHeight();
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
		canvas.setLineWidth(5);
		canvas.rectangle(20, 20, width - 40, height - 40);
		canvas.stroke();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
	
}
