package org.subscription.service.calculators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.subscription.entities.Subscription;
import org.subscription.entities.frequencies.Frequency;
import org.subscription.service.TestConfig;
import org.subscription.service.calculators.InvoiceDateCalculator;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class WeeklyInvoiceDateCalculatorTest {

    private Subscription subscription;
    private Calendar start;
    private Calendar end;
    private Frequency frequency;
    private int FEB = Calendar.FEBRUARY;

    @Autowired
    @Qualifier(value= "weeklyInvoiceDateCalculator")
    private InvoiceDateCalculator calculator;

    @Before
    public void setup() {
        subscription = mock(Subscription.class);
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        frequency = mock(Frequency.class);
        when(subscription.getFrequency()).thenReturn(frequency);
    }

    @After
    public void tearDown() {
        subscription = null;
        start = end = null;
        frequency = null;
    }

    @Test
    public void matchingInvoiceDates() {

        start.set(2018, FEB, 1);
        end.set(2018, FEB, 28);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("TUE");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Arrays.asList("06/02/2018", "13/02/2018", "20/02/2018", "27/02/2018");

        assertEquals(mockList, calculate);
        assertEquals(4, calculate.size());
    }

    @Test
    public void noInvoiceDates() {

        start.set(2018, FEB, 25);
        end.set(2018, FEB, 28);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("THU");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = new ArrayList<>();

        assertEquals(mockList, calculate);
        assertEquals(0, calculate.size());
    }

    @Test
    public void sameInvoiceDate() {

        start.set(2018, FEB, 25);
        end.set(2018, FEB, 28);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("SUN");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Collections.singletonList("25/02/2018");

        assertEquals(mockList, calculate);
        assertEquals(1, calculate.size());
    }
}