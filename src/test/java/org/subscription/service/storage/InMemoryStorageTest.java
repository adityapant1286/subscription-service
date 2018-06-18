package org.subscription.service.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Monthly;
import org.subscription.entities.frequencies.Weekly;
import org.subscription.entities.payment.Card;
import org.subscription.service.TestConfig;
import org.subscription.util.SubscriptionBuilder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class InMemoryStorageTest {

    private SubscriptionBuilder builder;
    private String user;
    private Calendar start;
    private Calendar end;

    @Autowired
    @Qualifier(value= "inMemoryStorage")
    private Storage storage;

    @Before
    public void setup() {
        user = "john";
        builder = new SubscriptionBuilder();
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.set(2018, Calendar.FEBRUARY, 1);
        end.set(2018, Calendar.FEBRUARY, 28);
    }

    @After
    public void tearDown() {
        user = null;
        builder = null;
        start = end = null;
        storage = null;
    }

    @Test
    public void addTest() throws Exception {

        Subscription weekly = builder.id(UUID.randomUUID())
                                    .startDate(start.getTime())
                                    .endDate(end.getTime())
                                    .frequency(new Weekly("FRI"))
                                    .paymentMethod(new Card(new BigDecimal(20)))
                                    .create();

        Subscription added = storage.store(user, weekly);

        assertEquals(weekly, added);
        assertEquals(1, storage.size());
        assertEquals(1, storage.getAll(user).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTestEx() throws Exception {

        Subscription subscription = builder.startDate(start.getTime())
                .endDate(end.getTime())
                .create();
    }

    @Test
    public void getSubscription() throws Exception {

        UUID uuid = UUID.randomUUID();
        Subscription monthly = builder.id(uuid)
                                    .startDate(start.getTime())
                                    .endDate(end.getTime())
                                    .frequency(new Monthly("10"))
                                    .paymentMethod(new Card(new BigDecimal(20)))
                                    .create();


        storage.store(user, monthly);
        assertEquals(monthly, storage.getById(user, uuid).orElse(null));
   }

}