/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>.
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
package cz.lbenda.coursing.service;

import org.springframework.security.crypto.bcrypt.BCrypt;

/** This class is used for generating password
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public abstract class AbstractPasswordGenerator {

  public final static String generatePassword(final String pass) {
    return BCrypt.hashpw(pass, BCrypt.gensalt(12));
  }

  public final static boolean checkPassword(final String candidate, final String stored) {
    return BCrypt.checkpw(candidate, stored);
  }
}
