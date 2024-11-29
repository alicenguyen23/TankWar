package edu.tcu.cs.tankwar.utils;

public class Pixel {
  public static double snapToPixel(double value, double pixelSize) {
    return Math.round(value / pixelSize) * pixelSize;
  }
}
