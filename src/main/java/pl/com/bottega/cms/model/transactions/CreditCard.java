package pl.com.bottega.cms.model.transactions;

public class CreditCard {

    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvc;

    private CreditCard() {
    }

    private CreditCard(String number, Integer expirationMonth, Integer expirationYear, String cvc) {
        this.number = number;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public String getCvc() {
        return cvc;
    }

}
