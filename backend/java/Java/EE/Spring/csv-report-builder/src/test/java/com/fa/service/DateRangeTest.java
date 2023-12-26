package com.fa.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateRangeTest {
    @Test
    public void withBoth() {
        final LocalDate startDate = LocalDate.of(2021,1,1);
        final LocalDate endDate = LocalDate.of(2021,12,31);
        DateRange tested = new DateRange(startDate, endDate);
        assertEquals(startDate.format(DateTimeFormatter.ISO_DATE), tested.getStartDate());
        assertEquals(endDate.format(DateTimeFormatter.ISO_DATE), tested.getEndDate());
    }
    @Test
    public void withStartDateOnly() {
        final LocalDate startDate = LocalDate.of(2021,1,1);
        DateRange tested = new DateRange(startDate, null);
        assertEquals(startDate.format(DateTimeFormatter.ISO_DATE), tested.getStartDate());
        assertEquals("", tested.getEndDate());
    }
    @Test
    public void withEndDateOnly() {
        final LocalDate endDate = LocalDate.of(2021,1,1);
        DateRange tested = new DateRange(null, endDate);
        assertEquals("", tested.getStartDate());
        assertEquals(endDate.format(DateTimeFormatter.ISO_DATE), tested.getEndDate());
    }
}
