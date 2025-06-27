package com.ecommerce.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.Instant;

@UtilityClass
public class TimeUtil {
    public static Timestamp currentTimestamp() {
        return Timestamp.from(Instant.now());
    }
}
