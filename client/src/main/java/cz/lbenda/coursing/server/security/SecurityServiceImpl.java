/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.server.security;

import cz.lbenda.coursing.service.SecurityService;
import cz.lbenda.coursing.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Service("SecurityService")
public class SecurityServiceImpl implements SecurityService {

  private static final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);

  @Autowired(required = true)
  private AuthenticationProvider authenticationProvider;
  @Autowired(required = true)
  private PasswordEncoder passwordEncoder;

  @Override
  public void login(String username, String password) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = authenticationProvider.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Override
  public final User getCurrentUser() {
    if (SecurityContextHolder.getContext().getAuthentication() == null) { return null; }
    return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
  }

  @Override
  public final void changePassword(User user, char[] oldPassword, char[] newPassword) throws BadCredentialsException {
    if (user == null) { throw new NullPointerException("The user object mustn't be null"); }
    if (((user.getPasswd() == null || user.getPasswd().length() == 0)
          && (oldPassword == null || oldPassword.length == 0)
         ) || passwordEncoder.matches(String.valueOf(oldPassword), user.getPasswd())) {
      user.setPasswd(passwordEncoder.encode(String.valueOf(newPassword)));
    } else {
      throw new BadCredentialsException("The old password didn't match to user password.");
    }
  }
}
