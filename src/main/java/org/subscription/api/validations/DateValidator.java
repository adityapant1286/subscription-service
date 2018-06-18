package org.subscription.api.validations;

import org.springframework.stereotype.Component;
import org.subscription.api.entities.SubscriptionRequest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.subscription.util.AppUtil.toLocalDate;

@Component
public class DateValidator {

    public List<String> validate(SubscriptionRequest request) {
        final LocalDate startDate = toLocalDate(request.getStartDate());
        final LocalDate endDate = toLocalDate(request.getEndDate());
        List<String> errors = new ArrayList<>(0);
        if (ChronoUnit.MONTHS.between(startDate, endDate) > 3)
            errors.add("Date error: Maximum 3 months allowed");

        return errors;
    }
}
