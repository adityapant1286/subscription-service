package org.subscription.service;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.subscription.api.validations.DateValidator;
import org.subscription.api.validations.FrequencyValidator;
import org.subscription.api.validations.SubscriptionValidator;
import org.subscription.service.calculators.DailyInvoiceDateCalculator;
import org.subscription.service.calculators.InvoiceDateCalculator;
import org.subscription.service.calculators.MonthlyInvoiceDateCalculator;
import org.subscription.service.calculators.WeeklyInvoiceDateCalculator;
import org.subscription.service.storage.InMemoryStorage;
import org.subscription.service.storage.Storage;

@Configuration
public class TestConfig {

    @Bean("weeklyInvoiceDateCalculator")
    public InvoiceDateCalculator weeklyCalculator() { return new WeeklyInvoiceDateCalculator(); }

    @Bean("dailyInvoiceDateCalculator")
    public InvoiceDateCalculator dailyCalculator() { return new DailyInvoiceDateCalculator(); }

    @Bean("monthlyInvoiceDateCalculator")
    public InvoiceDateCalculator monthlyCalculator() { return new MonthlyInvoiceDateCalculator(); }

    @Bean("inMemoryStorage")
    public Storage inMemory() { return new InMemoryStorage(); }

    @Bean("subscriptionCreator")
    public SubscriptionCreatorService subscriptionService() { return new SubscriptionCreator(); }

//    @Bean
//    public TestRestTemplate testRestTemplate() { return new TestRestTemplate(); }

    @Bean
    public SubscriptionValidator subscriptionValidator() { return new SubscriptionValidator(); }

    @Bean
    public DateValidator dateValidator() { return new DateValidator(); }

    @Bean
    public FrequencyValidator frequencyValidator() { return new FrequencyValidator(); }

}
