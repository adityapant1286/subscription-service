package org.subscription.entities;

import org.subscription.entities.frequencies.Daily;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.payment.PaymentMethod;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ConcreteSubscription implements Subscription {

    private UUID id;
    private PaymentMethod paymentMethod;
    private Frequency frequency;
    private Date startDate;
    private Date endDate;
    private Date createdOn = new Date();

    public ConcreteSubscription() { }

    public ConcreteSubscription(UUID id, PaymentMethod paymentMethod, Frequency frequency, Date startDate, Date endDate) {
        this.paymentMethod = paymentMethod;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public void setFrequency(Daily frequency) { this.frequency = frequency; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    @Override
    public UUID getId() { return id; }

    @Override
    public String getName() { return frequency.getName(); }

    @Override
    public PaymentMethod getPayment() { return paymentMethod; }

    @Override
    public Frequency getFrequency() { return frequency; }

    @Override
    public Date getStartDate() { return startDate; }

    @Override
    public Date getEndDate() { return endDate; }

    @Override
    public Date getCreatedOn() { return createdOn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcreteSubscription that = (ConcreteSubscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(paymentMethod, that.paymentMethod) &&
                Objects.equals(frequency, that.frequency) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() { return Objects.hash(id, paymentMethod, frequency, startDate, endDate, createdOn); }

    @Override
    public String toString() {
        return "ConcreteSubscription{" +
                "id=" + id.toString() +
                ", paymentMethod=" + paymentMethod.toString() +
                ", frequency=" + frequency.toString() +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", createdOn=" + createdOn.toString() +
                '}';
    }
}
