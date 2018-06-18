package org.subscription.entities.payment;

import java.math.BigDecimal;

public interface PaymentMethod {

    String getType();
    BigDecimal getAmount();
    String getCurrency();
}
