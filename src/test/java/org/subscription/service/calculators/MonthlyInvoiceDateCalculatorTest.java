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

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class MonthlyInvoiceDateCalculatorTest {

    private Subscription subscription;
    private Calendar start;
    private Calendar end;
    private Frequency frequency;

    @Autowired
    @Qualifier(value= "monthlyInvoiceDateCalculator")
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

        start.set(2018, Calendar.FEBRUARY, 1);
        end.set(2018, Calendar.MARCH, 31);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("13");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Arrays.asList("13/02/2018", "13/03/2018");

        assertEquals(mockList, calculate);
        assertEquals(2, calculate.size());
    }

    @Test
    public void noInvoiceDates() {

        start.set(2018, Calendar.FEBRUARY, 25);
        end.set(2018, Calendar.MARCH, 1);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("13");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = new ArrayList<>();

        assertEquals(mockList, calculate);
        assertEquals(0, calculate.size());
    }

    @Test
    public void sameInvoiceDate() {

        start.set(2018, Calendar.FEBRUARY, 25);
        end.set(2018, Calendar.MARCH, 22);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());
        when(frequency.getOccursOn()).thenReturn("25");

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Collections.singletonList("25/02/2018");

        assertEquals(mockList, calculate);
        assertEquals(1, calculate.size());
    }

}