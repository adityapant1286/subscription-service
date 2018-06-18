package org.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.payment.PaymentMethod;
import org.subscription.service.storage.Storage;
import org.subscription.util.SubscriptionBuilder;

import java.util.UUID;

@Component
public class SubscriptionCreator implements SubscriptionCreatorService {

    @Autowired
    @Qualifier("inMemoryStorage")
    private Storage inMemory;

    @Override
    public Subscription create(SubscriptionRequest request, PaymentMethod paymentMethod, Frequency frequency) {

        final Subscription subscription = createSubscription(request, paymentMethod, frequency);

        return inMemory.store(request.getUser(), subscription);
    }

    private Subscription createSubscription(SubscriptionRequest request, PaymentMethod paymentMethod, Frequency frequency) {
        final SubscriptionBuilder builder = new SubscriptionBuilder();

        return builder.id(UUID.randomUUID())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .frequency(frequency)
                .paymentMethod(paymentMethod)
                .create();
    }
}
