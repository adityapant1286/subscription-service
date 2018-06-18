package org.subscription.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.entities.frequencies.Weekly;
import org.subscription.entities.payment.Card;
import org.subscription.entities.payment.PaymentMethod;
import org.subscription.service.storage.Storage;
import org.subscription.util.SubscriptionBuilder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SubscriptionCreatorTest {

    @Autowired
    @Qualifier("inMemoryStorage")
    private Storage storage;

    @Autowired
    @Qualifier("subscriptionCreator")
    private SubscriptionCreatorService creator;

    private Calendar start;
    private Calendar end;


    @Before
    public void setup() {
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.set(2018, Calendar.FEBRUARY, 1);
        end.set(2018, Calendar.FEBRUARY, 28);
    }

    @After
    public void tearDown() {
        start = end = null;
        storage = null;
    }

    @Test
    public void test() {

        final SubscriptionRequest weeklyRequest = new SubscriptionRequest();
        weeklyRequest.setUser("john");
        weeklyRequest.setAmount(new BigDecimal(20));
        weeklyRequest.setFrequency("WEEKLY");
        weeklyRequest.setOccursOn("TUE");
        weeklyRequest.setStartDate(start.getTime());
        weeklyRequest.setEndDate(end.getTime());

        Subscription created = creator.create(weeklyRequest, new Card(weeklyRequest.getAmount()), new Weekly(weeklyRequest.getOccursOn()));

        assertEquals(weeklyRequest.getFrequency(), created.getFrequency().getName());
        assertEquals(weeklyRequest.getOccursOn(), created.getFrequency().getOccursOn());
        assertEquals(weeklyRequest.getAmount(), created.getPayment().getAmount());
        assertEquals(weeklyRequest.getStartDate(), created.getStartDate());
        assertEquals(weeklyRequest.getEndDate(), created.getEndDate());

        assertEquals(1, storage.size());
    }
}