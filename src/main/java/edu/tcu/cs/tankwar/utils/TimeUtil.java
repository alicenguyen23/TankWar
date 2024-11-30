package edu.tcu.cs.tankwar.utils;

public class TimeUtil {
  /* Convert a time value from nanoseconds to seconds */
  public static double nanoToSeconds(long nanoseconds) {
    return nanoseconds / 1_000_000_000.0;
  }
}
