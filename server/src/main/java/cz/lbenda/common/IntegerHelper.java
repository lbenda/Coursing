/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.lbenda.common;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public abstract class IntegerHelper {

  private static final Logger LOG = LoggerFactory.getLogger(IntegerHelper.class);

  public static String integerToString(Integer value) {
    if (value == null) { return null; }
    return value.toString();
  }

  public static Integer stringToInteger(String value) {
    if (value == null || "".equals(value.trim())) { return null; }
    if ("null".equals(value)) { return null; }
    return Integer.valueOf(value);
  }

  public static String isToString(InputStream is) {
    StringBuilder sb = new StringBuilder();
    byte[] b = new byte[4096];
    int l;
    try {
      while ((l = is.read(b)) > 0) {
        sb.append(new String(b, 0, l, "utf-8"));
      }
    } catch (IOException ex) {
      LOG.error("Error input when input stream is readed", ex);
    }
    return sb.toString();
  }
}
