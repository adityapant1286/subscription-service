package org.subscription.entities.frequencies;

import java.util.Objects;

public class Monthly implements Frequency {

    private String occursOn;

    public Monthly() { }

    public Monthly(String occursOn) { this.occursOn = occursOn; }

    public void setOccursOn(String occursOn) { this.occursOn = occursOn; }

    @Override
    public String getName() { return "MONTHLY"; }

    @Override
    public String getOccursOn() { return occursOn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monthly monthly = (Monthly) o;
        return Objects.equals(occursOn, monthly.occursOn);
    }

    @Override
    public int hashCode() { return Objects.hash(occursOn); }

    @Override
    public String toString() {
        return "Monthly{" +
                "occursOn='" + occursOn + '\'' +
                '}';
    }
}
