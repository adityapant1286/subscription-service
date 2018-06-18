package org.subscription.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subscription.api.entities.ErrorResponse;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.api.entities.SubscriptionResponse;
import org.subscription.api.validations.SubscriptionValidator;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Daily;
import org.subscription.entities.frequencies.Monthly;
import org.subscription.entities.frequencies.Weekly;
import org.subscription.entities.payment.Card;
import org.subscription.service.SubscriptionCreatorService;
import org.subscription.service.calculators.DailyInvoiceDateCalculator;
import org.subscription.service.calculators.MonthlyInvoiceDateCalculator;
import org.subscription.service.calculators.WeeklyInvoiceDateCalculator;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionResource {

    private String defaultUser = "john";

    @Autowired
    @Qualifier("subscriptionCreator")
    private SubscriptionCreatorService subscriptionService;

    @Autowired
    private SubscriptionValidator validator;

    @PostMapping
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionRequest request) {

        /*
        * In real-time user name will be retrieved from logged in user.
        * Security context or session
        * */
        request.setUser(defaultUser);

        return delegate(request);
    }

    private ResponseEntity<?> delegate(SubscriptionRequest request) {

        final ErrorResponse errorResponse = validator.validate(request);

        if (errorResponse.hasErrors())
            return new ResponseEntity<>(errorResponse, errorResponse.getStatus());

        final String frequency = request.getFrequency().toUpperCase();

        if ("WEEKLY".equals(frequency)) {
            Subscription subscription = subscriptionService.create(request, new Card(request.getAmount()), new Weekly(request.getOccursOn()));
            return new ResponseEntity<>(new SubscriptionResponse(subscription, new WeeklyInvoiceDateCalculator()), HttpStatus.CREATED);
        }

        if ("MONTHLY".equals(frequency)) {
            Subscription subscription = subscriptionService.create(request, new Card(request.getAmount()), new Monthly(request.getOccursOn()));
            return new ResponseEntity<>(new SubscriptionResponse(subscription, new MonthlyInvoiceDateCalculator()), HttpStatus.CREATED);
        }

        if ("DAILY".equals(frequency)) {
            Subscription subscription = subscriptionService.create(request, new Card(request.getAmount()), new Daily(request.getOccursOn()));
            return new ResponseEntity<>(new SubscriptionResponse(subscription, new DailyInvoiceDateCalculator()), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT, "Frequency", "Required frequency not present"), HttpStatus.NO_CONTENT);
    }
}
