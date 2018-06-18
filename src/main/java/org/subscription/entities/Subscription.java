package org.subscription.entities;

import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.payment.PaymentMethod;

import java.util.Date;
import java.util.UUID;

public interface Subscription {

    String getName();

    UUID getId();

    PaymentMethod getPayment();

    Frequency getFrequency();

    Date getStartDate();

    Date getEndDate();

    Date getCreatedOn();
}
