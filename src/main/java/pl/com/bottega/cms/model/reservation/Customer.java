package pl.com.bottega.cms.model.reservation;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by ogurekk on 2017-04-21.
 */
@Embeddable
public class Customer implements Serializable{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!firstName.equals(customer.firstName)) return false;
        if (!lastName.equals(customer.lastName)) return false;
        if (!email.equals(customer.email)) return false;
        return phone.equals(customer.phone);

    }
}
