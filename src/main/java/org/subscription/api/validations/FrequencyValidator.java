package org.subscription.api.validations;

import org.springframework.stereotype.Component;
import org.subscription.api.entities.SubscriptionRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FrequencyValidator {

    private static final List<String> DAY = Arrays.asList("MON", "MONDAY", "TUE", "TUESDAY", "WED", "WEDNESDAY",
                                                            "THU", "THURSDAY", "FRI", "FRIDAY", "SAT", "SATURDAY", "SUN", "SUNDAY");

    public List<String> validate(SubscriptionRequest request) {
        final String frequency = request.getFrequency();
        final List<String> errors = new ArrayList<>(0);

        if ("WEEKLY".equals(frequency) && !DAY.contains(request.getOccursOn().toUpperCase()))
            errors.add("Weekly: 'occursOn' should be name of the weekday");

        if ("MONTHLY".equals(frequency)) {
            try {
                Integer.valueOf(request.getOccursOn());
            } catch (NumberFormatException ex) {
                errors.add("Monthly: 'occursOn' should be numeric date");
            }
        }

        return errors;
    }
}
