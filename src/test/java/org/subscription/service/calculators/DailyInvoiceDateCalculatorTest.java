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
import org.subscription.service.TestConfig;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DailyInvoiceDateCalculatorTest {

    private Subscription subscription;
    private Calendar start;
    private Calendar end;
    private int FEB = Calendar.FEBRUARY;

    @Autowired
    @Qualifier(value= "dailyInvoiceDateCalculator")
    private InvoiceDateCalculator calculator;

    @Before
    public void setup() {
        subscription = mock(Subscription.class);
        start = Calendar.getInstance();
        end = Calendar.getInstance();
    }

    @After
    public void tearDown() {
        subscription = null;
        start = end = null;
    }

    @Test
    public void matchingInvoiceDates() {

        start.set(2018, FEB, 1);
        end.set(2018, FEB, 4);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());


        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Arrays.asList("01/02/2018", "02/02/2018", "03/02/2018", "04/02/2018");

        assertEquals(mockList, calculate);
        assertEquals(4, calculate.size());
    }

    @Test
    public void noInvoiceDates() {

        start.set(2018, FEB, 26);
        end.set(2018, FEB, 25);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = new ArrayList<>();

        assertEquals(mockList, calculate);
        assertEquals(0, calculate.size());
    }

    @Test
    public void sameInvoiceDate() {

        start.set(2018, FEB, 25);
        end.set(2018, FEB, 25);

        when(subscription.getStartDate()).thenReturn(start.getTime());
        when(subscription.getEndDate()).thenReturn(end.getTime());

        List<String> calculate = calculator.calculate(subscription);

        List<String> mockList = Collections.singletonList("25/02/2018");

        assertEquals(mockList, calculate);
        assertEquals(1, calculate.size());
    }

}