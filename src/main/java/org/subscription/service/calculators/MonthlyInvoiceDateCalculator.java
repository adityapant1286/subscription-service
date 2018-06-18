package org.subscription.service.calculators;

import org.springframework.stereotype.Service;
import org.subscription.entities.Subscription;
import org.subscription.util.AppUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonthlyInvoiceDateCalculator implements InvoiceDateCalculator {

    @Override
    public List<String> calculate(Subscription subscription) {

        LocalDate startDate = AppUtil.toLocalDate(subscription.getStartDate());
        LocalDate endDate = AppUtil.toLocalDate(subscription.getEndDate());

        final List<String> invoiceDates = new ArrayList<>(0);

        final Integer occursOn = Integer.valueOf(subscription.getFrequency().getOccursOn());

        LocalDate dayOfMonth = startDate.withDayOfMonth(occursOn);

        if (dayOfMonth.isBefore(startDate))
            dayOfMonth = dayOfMonth.plusMonths(1);

        while (!dayOfMonth.isAfter(endDate)) {
            invoiceDates.add(AppUtil.formatDate(dayOfMonth));
            dayOfMonth = dayOfMonth.plusMonths(1);
        }

        return invoiceDates;
    }
}
