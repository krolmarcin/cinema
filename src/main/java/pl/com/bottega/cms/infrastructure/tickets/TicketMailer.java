package pl.com.bottega.cms.infrastructure.tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import pl.com.bottega.cms.model.repositories.ReservationRepository;
import pl.com.bottega.cms.model.reservation.Customer;
import pl.com.bottega.cms.model.reservation.Reservation;
import pl.com.bottega.cms.model.reservation.ReservationNumber;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by ogurekk on 2017-05-07.
 */
public class TicketMailer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReservationRepository reservationRepository;

    public void sendTickets(ReservationNumber reservationNumber) {
        Reservation reservation = reservationRepository.get(reservationNumber);
        Customer customer = reservation.getCustomer();
        String email = customer.getEmail();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("cmsgroupb@gmail.com"));
                mimeMessage.setText(
                        "Dear " + customer.getFirstName() + " "
                                + customer.getLastName()
                                + ", thank you for placing order. Your order number is "
                                + reservationNumber.getNumber());
            }
        };
        javaMailSender.send(preparator);


    }
}
