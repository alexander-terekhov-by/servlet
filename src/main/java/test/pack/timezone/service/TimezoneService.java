package test.pack.timezone.service;

import test.pack.timezone.entity.ZipCode;

import java.util.TimeZone;


public interface TimezoneService {
    TimeZone getTimezone(ZipCode zipCode);
}
