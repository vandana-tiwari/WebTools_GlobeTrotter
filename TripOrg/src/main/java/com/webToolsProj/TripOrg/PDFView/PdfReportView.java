package com.webToolsProj.TripOrg.PDFView;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.webToolsProj.TripOrg.POJO.Booking;

public class PdfReportView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document pdfdoc, PdfWriter pdfwriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Font font_times_16_bold_green = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.GREEN);
		Font font_times_16_bold_black = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL, Color.BLACK);
		Font font_times_16_italic_black = new Font(Font.TIMES_ROMAN, 16, Font.ITALIC, Color.BLACK);
		Font font_times_16_bold_blue = new Font(Font.TIMES_ROMAN, 16, Font.ITALIC, Color.BLUE);
		Booking b = (Booking) session.getAttribute("bookingPDF");
		
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(font_times_16_bold_green);
		paragraph.add("Hello " + b.getUser().getfName()+" "+ b.getUser().getlName());
		paragraph.add(" , your reservation is booked and confirmed");
		pdfdoc.add(paragraph);
		pdfdoc.add(Chunk.NEWLINE);
		
		Phrase ph = new Phrase("Reservation Details:");
		pdfdoc.add(ph);
		pdfdoc.add(Chunk.NEWLINE);

		Table table = new Table(2);
		//table.addColumns(2);
		table.addCell("Booking Date:");
		table.addCell(String.valueOf(b.getBookingDate()));
		table.addCell("Booking ID:");
		table.addCell(String.valueOf(b.getBookingID()));
		table.addCell("Check In:");
		table.addCell(String.valueOf(b.getCheckInDate()));
		table.addCell("Check Out:");
		table.addCell(String.valueOf(b.getCheckOutDate()));
		table.addCell("Number of Occupants:");
		table.addCell(String.valueOf(b.getOccupants()));
		table.addCell("Rooms:");
		table.addCell(String.valueOf(b.getRooms()));
		table.addCell("Price per Night:");
		table.addCell(String.valueOf(b.getAdvertisementApartment().getPrice()));
		table.addCell("Furnished:");
		table.addCell(String.valueOf(b.getAdvertisementApartment().getFurnished()));
		table.addCell("Number of Beds:");
		table.addCell(String.valueOf(b.getAdvertisementApartment().getNumOfBeds()));
		table.addCell("Apartment Host:");
		table.addCell(String.valueOf(b.getAdvertisementApartment().getPerson().getfName())+" "+ String.valueOf(b.getAdvertisementApartment().getPerson().getlName()));
		table.addCell("Host Contact");
		table.addCell(String.valueOf(b.getAdvertisementApartment().getPerson().getEmail().getEmailId()));
		pdfdoc.add(table);

		Phrase p = new Phrase("Need help with your reservation?",font_times_16_bold_blue);
		pdfdoc.add(p);
		pdfdoc.add(Chunk.NEWLINE);
		Paragraph pg = new Paragraph();
		pg.setFont(font_times_16_italic_black);
		pg.add("Call us on +1 800 000 1111");
		pg.add("For faster service, mention booking#"+b.getBookingID());
		pg.setAlignment(Element.ALIGN_JUSTIFIED);
		pdfdoc.add(pg);
		pdfdoc.add(Chunk.NEWLINE);
		pdfdoc.add(Chunk.NEWLINE);

		pdfdoc.close();

	}

}