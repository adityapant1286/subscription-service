package org.subscription.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.api.entities.SubscriptionResponse;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class InMemorySubscriptionServiceTest {

    private String user;
    private Calendar start;
    private Calendar end;

    @Autowired
    @Qualifier("inMemorySubscriptionService")
    private SubscriptionCreatorService service;

    @Before
    public void setup() {
        user = "john";
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.set(2018, Calendar.FEBRUARY, 1);
        end.set(2018, Calendar.FEBRUARY, 28);
    }

    @After
    public void tearDown() {
        user = null;
        start = end = null;
        service = null;
    }

    @Test
    public void createWeeklySubscription() {
        final SubscriptionRequest weeklyRequest = new SubscriptionRequest();
        weeklyRequest.setUser(user);
        weeklyRequest.setAmount(new BigDecimal(20));
        weeklyRequest.setFrequency("WEEKLY");
        weeklyRequest.setOccursOn("TUE");
        weeklyRequest.setStartDate(start.getTime());
        weeklyRequest.setEndDate(end.getTime());

//        SubscriptionResponse weekly = service.createSubscription(weeklyRequest);
//
//        assertEquals(weeklyRequest.getAmount(), weekly.getAmount().getAmount());
//        assertEquals(weeklyRequest.getFrequency(), weekly.getSubscription_type());
//        assertEquals(4, weekly.getInvoice_dates().size());
    }

    @Test
    public void createMonthlySubscription() {
        final SubscriptionRequest monthlyRequest = new SubscriptionRequest();
        monthlyRequest.setUser(user);
        monthlyRequest.setAmount(new BigDecimal(30));
        monthlyRequest.setFrequency("MONTHLY");
        monthlyRequest.setOccursOn("10");
        monthlyRequest.setStartDate(start.getTime());
        monthlyRequest.setEndDate(end.getTime());

//        SubscriptionResponse monthly = service.createSubscription(monthlyRequest);
//
//        assertEquals(monthlyRequest.getAmount(), monthly.getAmount().getAmount());
//        assertEquals(monthlyRequest.getFrequency(), monthly.getSubscription_type());
//        assertEquals(1, monthly.getInvoice_dates().size());
    }

    @Test
    public void createDailySubscription() {
        final SubscriptionRequest dailyRequest = new SubscriptionRequest();
        dailyRequest.setUser(user);
        dailyRequest.setAmount(new BigDecimal(10));
        dailyRequest.setFrequency("DAILY");
        dailyRequest.setOccursOn("");
        dailyRequest.setStartDate(start.getTime());
        dailyRequest.setEndDate(end.getTime());

//        SubscriptionResponse daily = service.createSubscription(dailyRequest);
//
//        assertEquals(dailyRequest.getAmount(), daily.getAmount().getAmount());
//        assertEquals(dailyRequest.getFrequency(), daily.getSubscription_type());
//        assertEquals(28, daily.getInvoice_dates().size());
    }
}