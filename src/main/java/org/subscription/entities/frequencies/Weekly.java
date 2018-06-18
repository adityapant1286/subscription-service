package org.subscription.entities.frequencies;

import java.util.Objects;

public class Weekly implements Frequency {

    private String occursOn;

    public Weekly() { }

    public Weekly(String occursOn) { this.occursOn = occursOn; }

    public void setOccursOn(String occursOn) { this.occursOn = occursOn; }

    @Override
    public String getName() { return "WEEKLY"; }

    @Override
    public String getOccursOn() { return occursOn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weekly weekly = (Weekly) o;
        return Objects.equals(occursOn, weekly.occursOn);
    }

    @Override
    public int hashCode() { return Objects.hash(occursOn); }

    @Override
    public String toString() {
        return "Weekly{" +
                "occursOn='" + occursOn + '\'' +
                '}';
    }
}
