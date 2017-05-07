package pl.com.bottega.cms.infrastructure.tickets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.com.bottega.cms.application.TicketPrinter;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.DetailedSeat;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;
import pl.com.bottega.cms.model.reservation.ReservationStatus;
import pl.com.bottega.cms.ui.InvalidActionException;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Created by maciek on 06.05.2017.
 */
public class ITextTicketPrinter implements TicketPrinter {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private ReservationRepository reservationRepository;

    public ITextTicketPrinter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public byte[] printTicket(ReservationNumber reservationNumber) throws FileNotFoundException, DocumentException {

        Reservation reservation = reservationRepository.get(reservationNumber);
        if (reservation.getReservationStatus() != ReservationStatus.PAID){
            throw new InvalidActionException("You can't print tickets before paid");
        }
        Document document = new Document(PageSize.A4, 36f, 36f, 50f, 50f);
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        createPdf(document, reservation);
        document.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    private void createPdf(Document document, Reservation reservation) throws DocumentException {
        Set<DetailedSeat> detailedSeats = reservation.getDetailedSeats();
        for (DetailedSeat detailedSeat : detailedSeats) {
            float[] columnWidths = {1, 1};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            Font f1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYWHITE);
            Font f2 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, GrayColor.GRAYBLACK);
            PdfPCell cell = new PdfPCell(new Phrase("Numer rezerwacji: " + reservation.getReservationNumber().getNumber(), f1));
            cell.setBackgroundColor(GrayColor.GRAYBLACK);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(9);
            table.addCell(cell);
            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell("Nazwa seansu: " + '\n' + reservation.getShowing().getMovie().getTitle() + '\n'
                    + "Czas trwania seansu: " + reservation.getShowing().getMovie().getLength() + " min");
            table.addCell(new Phrase("Data i godzina seansu: " + '\n'
                    + reservation.getShowing().getBeginsAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy " + "HH:mm")), f2));
            table.addCell("Kino: " + reservation.getShowing().getCinema().getName() + ", "
                    + reservation.getShowing().getCinema().getCity());
            table.addCell(new Phrase("Rzad: " + detailedSeat.getRow() + ", Miejsce: " + detailedSeat.getSeat(), f2));
            document.add(table);
            document.add(new Paragraph(" "));
        }
    }
}
