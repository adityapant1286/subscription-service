package org.subscription.service.calculators;

import org.subscription.entities.Subscription;

import java.util.List;


public interface InvoiceDateCalculator {
    List<String> calculate(Subscription subscription);
}
