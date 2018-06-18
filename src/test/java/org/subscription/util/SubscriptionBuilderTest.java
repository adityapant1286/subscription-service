package org.subscription.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Weekly;
import org.subscription.entities.payment.Card;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class SubscriptionBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void exception() {

        new SubscriptionBuilder().endDate(new Date()).startDate(new Date()).id(UUID.randomUUID()).create();
    }

    @Test
    public void build() {

        Subscription subscription = new SubscriptionBuilder().id(UUID.randomUUID())
                .startDate(new Date())
                .endDate(new Date())
                .paymentMethod(new Card(new BigDecimal(20)))
                .frequency(new Weekly("WED"))
                .create();

        assertEquals("WED", subscription.getFrequency().getOccursOn());
        assertEquals(new BigDecimal(20), subscription.getPayment().getAmount());
        assertEquals("WEEKLY", subscription.getName());
    }
}