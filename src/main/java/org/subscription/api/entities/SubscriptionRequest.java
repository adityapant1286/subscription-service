package org.subscription.api.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionRequest {

    private String user;

    @NotNull(message = "'amount' must not be blank")
    private BigDecimal amount;

    @NotBlank(message = "'frequency' must not be blank (e.g. DAILY or WEEKLY or MONTHLY)")
    private String frequency;

    @NotBlank(message = "'occursOn' must not be blank")
    private String occursOn;

    @NotNull(message = "'startDate' must not be blank")
    private Date startDate;

    @NotNull(message = "'endDate' must not be blank")
    private Date endDate;

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getFrequency() { return frequency; }

    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getOccursOn() { return occursOn; }

    public void setOccursOn(String occursOn) { this.occursOn = occursOn; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
