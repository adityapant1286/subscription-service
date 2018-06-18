package org.subscription.entities.frequencies;

import java.util.Objects;

public class Daily implements Frequency {

    private String occursOn;

    public Daily() { }

    public Daily(String occursOn) { this.occursOn = occursOn; }

    public void setOccursOn(String occursOn) { this.occursOn = occursOn; }

    @Override
    public String getName() { return "DAILY"; }

    @Override
    public String getOccursOn() { return occursOn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Daily daily = (Daily) o;
        return Objects.equals(occursOn, daily.occursOn);
    }

    @Override
    public int hashCode() { return Objects.hash(occursOn); }

    @Override
    public String toString() {
        return "Daily{" +
                "occursOn='" + occursOn + '\'' +
                '}';
    }
}
