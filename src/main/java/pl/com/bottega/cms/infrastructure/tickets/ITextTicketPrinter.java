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
        document.add(new Paragraph("This is test"));
        document.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}
