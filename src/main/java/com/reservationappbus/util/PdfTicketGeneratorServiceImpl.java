package com.reservationappbus.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.reservationappbus.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;


@Service
public class PdfTicketGeneratorServiceImpl {


    public byte[] generateTicket(Passenger passenger, String fromLocation, String toLocation, String fromDate, String toDate) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            // Add ticket information to the PDF using iText
            document.add(new Paragraph("Ticket Information"));
            document.add(new Paragraph("Name: " + passenger.getFirstName() + " " + passenger.getLastName()));
            document.add(new Paragraph("Email: " + passenger.getEmail()));
            document.add(new Paragraph("Mobile: " + passenger.getMobile()));
            document.add(new Paragraph("Bus ID: " + passenger.getBusId()));  // Additional field
            document.add(new Paragraph("Route ID: " + passenger.getRouteId()));
            document.add(new Paragraph("FromLocation: " + fromLocation));
            document.add(new Paragraph("ToLocation: " + toLocation));
            document.add(new Paragraph("FromDate: " + fromDate));
            document.add(new Paragraph("ToDate: " + toDate)); // Additional field
            // Add more ticket information as needed

            document.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
