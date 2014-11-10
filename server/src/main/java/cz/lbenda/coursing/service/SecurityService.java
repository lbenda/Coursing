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
package cz.lbenda.coursing.service;

import cz.lbenda.coursing.user.User;
import org.springframework.security.authentication.BadCredentialsException;

/** Service which is used for authentication etc.
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface SecurityService {
  public  static final String SERVICE_NAME = "SecurityService";

  public interface SecurityListener {
    void onLogin();
    void onLogout();
  }

  /** login user */
  void login(String username, String password);

  /** Logout current logged user */
  void logout();

  /** Return current logged user object */
  User getCurrentUser();

  /** Change password of user
   * @param user user which password is changed
   * @param oldPassword old password in unrypted form
   * @param newPassword new password in uncrypted form
   * @throws BadCredentialsException when the old password didn't match to user password
   */
  void changePassword(User user, char[] oldPassword, char[] newPassword) throws BadCredentialsException ;

  void addSecurityListener(SecurityListener l);
  void removeSecurityListener(SecurityListener l);
}
