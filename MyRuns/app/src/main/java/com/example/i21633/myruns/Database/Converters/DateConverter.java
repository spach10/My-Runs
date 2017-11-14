package com.example.i21633.myruns.Database.Converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by i21633 on 10/18/17.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
