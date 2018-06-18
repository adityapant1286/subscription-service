package org.subscription.api.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.subscription.api.entities.ErrorResponse;
import org.subscription.api.entities.SubscriptionRequest;

import java.util.List;

@Component
public class SubscriptionValidator {

    @Autowired
    private DateValidator dateValidator;

    @Autowired
    private FrequencyValidator frequencyValidator;


    public ErrorResponse validate(SubscriptionRequest request) {
        List<String> errors = dateValidator.validate(request);
        errors.addAll(frequencyValidator.validate(request));

        return createErrorResponse(errors);
    }

    private ErrorResponse createErrorResponse(List<String> errors) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation error", errors);
    }

}
