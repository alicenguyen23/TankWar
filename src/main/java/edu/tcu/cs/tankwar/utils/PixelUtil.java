package edu.tcu.cs.tankwar.utils;

public class PixelUtil {

  /* Adjusts the given value to the nearest multiple of the specified pixel size */
  public static double snapToPixel(double value, double pixelSize) {
    return Math.round(value / pixelSize) * pixelSize;
  }

}
