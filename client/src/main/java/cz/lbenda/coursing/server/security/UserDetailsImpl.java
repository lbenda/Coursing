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
package cz.lbenda.coursing.server.security;

import cz.lbenda.coursing.server.user.UserImpl;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** Implementation of User for security reason. It's wrapper for {@see User}
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
public class UserDetailsImpl implements UserDetails {

  private static final Logger LOG = LoggerFactory.getLogger(UserDetailsImpl.class);

  public static class GrantedAuthorityImpl implements GrantedAuthority {
    private final String role;
    public GrantedAuthorityImpl(String role) { this.role = role; }
    public @Override String getAuthority() { return role; }
  };

  public static final User NULL_USER = new UserImpl();

  static {
    NULL_USER.setFirstName(null);
    NULL_USER.setLastName(null);
    NULL_USER.setUsername(null);
    NULL_USER.setLocked(true);
  }

  /** Wrapped user object */
  private final User user;

  /** Constructor
   * @param user user which is used for this. If user is null, then is used HOST values.
   */
  public UserDetailsImpl(User user) {
    this.user = user == null ? NULL_USER : user;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> result = new ArrayList<>();
    for (UserRole role : user.getRoles()) {
      LOG.trace("Role addedd of athorities: " + role.name());
      result.add(role.toGrantedAuthority());
    }
    return result;
  }

  @Override
  public String getPassword() { return user.getPasswd(); }
  @Override
  public String getUsername() { return user.getUsername(); }
  @Override
  public boolean isAccountNonExpired() { return user.getValidTo() == null; }
  @Override
  public boolean isAccountNonLocked() { return !user.isLocked(); }
  @Override
  public boolean isCredentialsNonExpired() { return true; }
  @Override
  public boolean isEnabled() {
    if (user == NULL_USER) { return false; }
    return !user.isLocked() && (user.getValidTo() == null || (new Date()).compareTo(user.getValidTo()) >= 0)
            && (user.getValidFrom() == null || (new Date()).compareTo(user.getValidFrom()) <= 0);
  }

  public final User getUser() {
    return user;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("username=").append(user.getUsername());
    sb.append("; roles=").append(UserRole.toString(user.getRoles()));
    return sb.toString();
  }
}
