package org.subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.api.entities.SubscriptionResponse;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Daily;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.frequencies.Monthly;
import org.subscription.entities.frequencies.Weekly;
import org.subscription.entities.payment.Card;
import org.subscription.entities.payment.PaymentMethod;
import org.subscription.service.calculators.InvoiceDateCalculator;
import org.subscription.service.storage.Storage;
import org.subscription.util.SubscriptionBuilder;

import java.util.List;
import java.util.UUID;

@Deprecated
public class InMemorySubscriptionService {

    @Autowired
    @Qualifier("inMemoryStorage")
    private Storage inMemory;

    @Autowired
    @Qualifier("dailyInvoiceDateCalculator")
    private InvoiceDateCalculator dailyCalculator;

    @Autowired
    @Qualifier("weeklyInvoiceDateCalculator")
    private InvoiceDateCalculator weeklyCalculator;

    @Autowired
    @Qualifier("monthlyInvoiceDateCalculator")
    private InvoiceDateCalculator monthlyCalculator;

//    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
//
//        final Subscription added = inMemory.store(request.getUser(), create(request));
//
//        return createResponse(added);
//    }

//    private Subscription create(SubscriptionRequest request) {
//
//        final SubscriptionBuilder builder = new SubscriptionBuilder();
//        final PaymentMethod card = new Card(request.getAmount());
//
//        return builder.id(UUID.randomUUID())
//                        .startDate(request.getStartDate())
//                        .endDate(request.getEndDate())
//                        .frequency(createFrequency(request.getFrequency(), request.getOccursOn()))
//                        .paymentMethod(card)
//                        .create();
//    }
//
//    private Frequency createFrequency(String type, String occursOn) {
//        type = type.toUpperCase();
//        if ("DAILY".equals(type))
//            return new Daily(occursOn);
//
//        if ("MONTHLY".equals(type))
//            return new Monthly(occursOn);
//
//        return new Weekly(occursOn);
//    }

//    private SubscriptionResponse createResponse(Subscription subscription) {
//        return new SubscriptionResponse(subscription, invoiceDates(subscription));
//    }

//    private List<String> invoiceDates(Subscription subscription) {
//        final String name = subscription.getFrequency().getName();
//
//        if ("DAILY".equals(name))
//            return dailyCalculator.calculate(subscription);
//
//        if ("MONTHLY".equals(name))
//            return monthlyCalculator.calculate(subscription);
//
//        return weeklyCalculator.calculate(subscription);
//    }
}
