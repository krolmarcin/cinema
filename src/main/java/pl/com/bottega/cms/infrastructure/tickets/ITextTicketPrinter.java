package pl.com.bottega.cms.infrastructure.tickets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Tab;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.DetailedSeat;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationItem;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
        Document document = new Document(PageSize.A4, 36f, 36f, 50f, 50f);
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        createPdf(document, reservation);
        document.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    private void createPdf(Document document, Reservation reservation) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{160, 120});
        table.setLockedWidth(true);
        Set<DetailedSeat> detailedSeats = reservation.getDetailedSeats();
        for (DetailedSeat detailedSeat : detailedSeats) {
            String reservationNumber = reservation.getReservationNumber().getNumber();
            Paragraph line1 = new Paragraph();
        line1.setAlignment(Element.ALIGN_MIDDLE);
        line1.add("Numer rezerwacji: " + reservation.getReservationNumber().getNumber());
        line1.add(String.format("            Data i godzina seansu: " + reservation.getShowing().getBeginsAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy " + "HH:mm"))));
        Paragraph line2 = new Paragraph();
        line2.setAlignment(Element.ALIGN_LEFT);
        line2.add("Nazwa seansu: " + reservation.getShowing().getMovie().getTitle());
        Paragraph line3 = new Paragraph();
        line3.setAlignment(Element.ALIGN_LEFT);
        line3.add("Kino: " + reservation.getShowing().getCinema().getName() + ", " + reservation.getShowing().getCinema().getCity());
        Paragraph line4 = new Paragraph();
        line4.setAlignment(Element.ALIGN_LEFT);
        line4.add("Rzad: " + detailedSeat.getRow() + ", Miejsce: " + detailedSeat.getSeat());
        Paragraph line5 = new Paragraph();
        line5.add("---------------------------------------------------------");
        document.add(line1);
        document.add(line2);
        document.add(line3);
        document.add(line4);
        document.add(line5);
        }
    }
}
