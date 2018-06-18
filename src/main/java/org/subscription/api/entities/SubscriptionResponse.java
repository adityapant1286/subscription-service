package org.subscription.api.entities;

import org.subscription.entities.Subscription;
import org.subscription.entities.payment.PaymentMethod;
import org.subscription.service.calculators.InvoiceDateCalculator;

import java.util.List;
import java.util.UUID;

public class SubscriptionResponse {

    private UUID id;
    private PaymentMethod amount;
    private String subscription_type;
    private List<String> invoice_dates;

    public SubscriptionResponse(Subscription subscription, InvoiceDateCalculator calculator) {
        this.id = subscription.getId();
        this.amount = subscription.getPayment();
        this.subscription_type = subscription.getFrequency().getName();
        this.invoice_dates = calculator.calculate(subscription);
    }

    public UUID getId() { return id; }

//    public void setId(UUID id) { this.id = id; }

    public PaymentMethod getAmount() { return amount; }

//    public void setAmount(PaymentMethod amount) { this.amount = amount; }

    public String getSubscription_type() { return subscription_type; }

//    public void setSubscription_type(String subscription_type) { this.subscription_type = subscription_type; }

    public List<String> getInvoice_dates() { return invoice_dates; }

//    public void setInvoice_dates(List<String> invoice_dates) { this.invoice_dates = invoice_dates; }
}
