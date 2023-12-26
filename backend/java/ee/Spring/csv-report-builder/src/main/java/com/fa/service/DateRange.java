package com.fa.service;

import java.time.LocalDate;

public final class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;
    DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getStartDate() {
        return startDate == null ? "" : startDate.toString();
    }
    public String getEndDate() {
        return endDate == null ? "" : endDate.toString();
    }
}
