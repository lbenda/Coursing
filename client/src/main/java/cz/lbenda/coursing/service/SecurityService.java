/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
