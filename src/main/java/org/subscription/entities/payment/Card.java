package org.subscription.entities.payment;

import java.math.BigDecimal;
import java.util.Objects;

public class Card implements PaymentMethod {

    private BigDecimal amount;
    private String currency = "AUD";

    public Card() {}

    public Card(BigDecimal amount) {
        this.amount = amount;
    }

    public Card(BigDecimal amount, String currency) {
        this(amount);
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public void setCurrency(String currency) { this.currency = currency; }

    @Override
    public String getType() { return "Card"; }

    @Override
    public BigDecimal getAmount() { return amount; }

    @Override
    public String getCurrency() { return currency; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(amount, card.amount) &&
                Objects.equals(currency, card.currency);
    }

    @Override
    public int hashCode() { return Objects.hash(amount, currency); }

    @Override
    public String toString() {
        return "Card{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
