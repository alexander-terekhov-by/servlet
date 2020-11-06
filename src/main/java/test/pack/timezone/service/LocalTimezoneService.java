package test.pack.timezone.service;

import test.pack.timezone.entity.ZipCode;

import java.util.TimeZone;


public class LocalTimezoneService implements TimezoneService {
    @Override
    public TimeZone getTimezone(ZipCode zipCode) {
        // go to any calculation of timezone
        return TimeZone.getDefault();
    }
}
