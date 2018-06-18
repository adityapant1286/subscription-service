package org.subscription.service;

import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.payment.PaymentMethod;

public interface SubscriptionCreatorService {

    Subscription create(SubscriptionRequest request, PaymentMethod paymentMethod, Frequency frequency);
}
