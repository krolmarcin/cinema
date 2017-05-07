package pl.com.bottega.cms.infrastructure.tickets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import java.io.*;

/**
 * Created by maciek on 06.05.2017.
 */
public class ITextTicketPrinter implements TicketPrinter {

    //private static final String RESULT = "ticket.pdf";
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private ReservationRepository reservationRepository;

    public ITextTicketPrinter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public byte[] printTicket(ReservationNumber reservationNumber) throws FileNotFoundException, DocumentException {

        Reservation reservation = reservationRepository.get(reservationNumber);
        Document document = new Document(PageSize.A4, 36f, 72f, 108f, 180f);
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        createPdf(document, reservation);
        document.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    private void createPdf(Document document, Reservation reservation) throws DocumentException {
        String reservationNumber = reservation.getReservationNumber().getNumber();
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.add("Numer rezerwacji: " + reservation.getReservationNumber().getNumber());
        document.add(paragraph);
    }
}
