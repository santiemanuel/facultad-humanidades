package com.milprogramadores.model;

import java.io.File;
import java.util.List;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Table;

public class PdfCreatorHistorial {

	private Certificado certificado;
	private Alumno alumno;
	private File file;
	
	public PdfCreatorHistorial(Certificado certificado, Alumno alumno, File file) {
		this.certificado = certificado;
		this.setAlumno(alumno);
		this.setFile(file);
	}
	
	public void createPdf() {
		try {
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(getFile()));
			
			Document doc = new Document(pdfDoc);
			
			PdfFont arial = PdfFontFactory.createFont("/fonts/Arial.ttf");

			pdfDoc.addNewPage();
			
			Text title = new Text("Alumno: " + alumno.getAlumno_nombre() + " " + alumno.getAlumno_apellido()).setFont(arial).setFontSize(10).setBold();
			Paragraph p1 = new Paragraph();
			p1.setTextAlignment(TextAlignment.CENTER);
			p1.setMarginTop(30);
			p1.add(title);
			
			//crear la tabla
			float[] colWidths = {1, 6, 5, 4, 1};
			Table table = new Table(UnitValue.createPercentArray(colWidths));
			
			Cell cell = new Cell(1, 5)
					.add(new Paragraph("Historial Académico"))
					.setFont(arial)
					.setFontSize(10)
					.setFontColor(DeviceGray.WHITE)
	                .setBackgroundColor(DeviceGray.BLACK)
	                .setTextAlignment(TextAlignment.CENTER);
			
			table.addHeaderCell(cell);
			
			Cell[] headerFooter = new Cell[]{
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("ID")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Carrera")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Materia")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Fecha")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Nota"))
            };
			
	        for (Cell hfCell : headerFooter) {
	        	table.addHeaderCell(hfCell);
	        }

			List<Historial> historial = certificado.getEstadoCurricular();
			
			for (Historial h: historial) {
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(h.getExamen_id().toString())).setFontSize(8));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(h.getCarrera_nombre())).setFontSize(8));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(h.getMateria_nombre())).setFontSize(8));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(h.getFecha().toString())).setFontSize(8));
				table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(h.getNota().toString())).setFontSize(8));
			}
			
			doc.add(p1);
			doc.add(table);
			drawBorder(pdfDoc);
			
			doc.close();
			pdfDoc.close();
		} catch (Exception e) {
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
	
	public Certificado getCertificado() {
		return certificado;
	}

	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

}
