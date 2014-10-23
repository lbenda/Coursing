/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.common;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public abstract class IntegerHelper {
  public static String integerToString(Integer value) {
    if (value == null) { return null; }
    return value.toString();
  }

  public static Integer stringToInteger(String value) {
    if (value == null || "".equals(value.trim())) { return null; }
    if ("null".equals(value)) { return null; }
    return Integer.valueOf(value);
  }
}
