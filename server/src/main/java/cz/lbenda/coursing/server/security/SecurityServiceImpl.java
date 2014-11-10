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
package cz.lbenda.coursing.server.security;

import cz.lbenda.coursing.service.SecurityService;
import cz.lbenda.coursing.user.User;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
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

  private final Set<SecurityListener> listeners
          = Collections.newSetFromMap(new WeakHashMap<SecurityListener, Boolean>());

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
    for (SecurityListener l : listeners) { l.onLogin(); }
  }

  @Override
  public void logout() {
    SecurityContextHolder.getContext().setAuthentication(null);
    for (SecurityListener l : listeners) { l.onLogout(); }
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

  public void addSecurityListener(SecurityListener l) {
    this.listeners.add(l);
  }

  public void removeSecurityListener(SecurityListener l) {
    this.listeners.remove(l);
  }
}
