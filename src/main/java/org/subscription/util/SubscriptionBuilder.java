package org.subscription.util;

import org.subscription.entities.*;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.payment.PaymentMethod;

import java.util.Date;
import java.util.UUID;

import static org.subscription.util.AppUtil.isNull;

public class SubscriptionBuilder {

    private UUID id;
    private PaymentMethod paymentMethod;
    private Frequency frequency;
    private Date startDate;
    private Date endDate;

    public SubscriptionBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public SubscriptionBuilder paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public SubscriptionBuilder frequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public SubscriptionBuilder startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public SubscriptionBuilder endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Subscription create() {

        if (isNull(id, paymentMethod, frequency, startDate, endDate))
            throw new IllegalArgumentException("All values are mandatory to create a Subscription");

        return new ConcreteSubscription(id, paymentMethod, frequency, startDate, endDate);
    }
}
