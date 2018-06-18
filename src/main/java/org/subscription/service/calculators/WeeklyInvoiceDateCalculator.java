package org.subscription.service.calculators;

import org.springframework.stereotype.Service;
import org.subscription.entities.Subscription;
import org.subscription.util.AppUtil;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static org.subscription.util.AppUtil.getDayOfWeek;

@Service
public class WeeklyInvoiceDateCalculator implements InvoiceDateCalculator {

    @Override
    public List<String> calculate(Subscription subscription) {

        LocalDate startDate = AppUtil.toLocalDate(subscription.getStartDate());
        LocalDate endDate = AppUtil.toLocalDate(subscription.getEndDate());

        final TemporalAdjuster temporalAdjuster = TemporalAdjusters.nextOrSame(getDayOfWeek(subscription.getFrequency().getOccursOn()));

        final List<String> invoiceDates = new ArrayList<>(0);

        if (!getNextDate(startDate, temporalAdjuster).isAfter(endDate)) {

            while (!startDate.isAfter(endDate)) {
                invoiceDates.add(AppUtil.formatDate(getNextDate(startDate, temporalAdjuster)));
                startDate = startDate.plusWeeks(1);
            }
        }

        return invoiceDates;
    }

    private static LocalDate getNextDate(LocalDate startDate, TemporalAdjuster temporalAdjuster) {
        final LocalDate sameOrNextDay = startDate.with(temporalAdjuster);
        return startDate.compareTo(sameOrNextDay) == 0 ? startDate : sameOrNextDay;
    }
}
