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
package cz.lbenda.coursing.user;

import cz.lbenda.coursing.server.security.UserDetailsImpl.GrantedAuthorityImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/** Enumeration of all user roles which is defined in application
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public enum UserRole {
  ADMIN, USER, GUEST, ;

  private static final String ROLE_PREFIX = "ROLE_";

  public static String toString(UserRole[] roles) {
    StringBuilder result = new StringBuilder();
    for (UserRole role : roles) {
      if (result.length() > 0) { result.append(","); }
      result.append(role.name());
    }
    return result.toString();
  }

  public static UserRole[] toArray(String roles) {
    List<UserRole> result = new ArrayList<>();
    if (roles == null || "".equals(roles.trim())) { return new UserRole[0]; }
    for (String role : roles.split(",")) {
      result.add(UserRole.valueOf(role.trim()));
    }
    return result.toArray(new UserRole[result.size()]);
  }

  public GrantedAuthority toGrantedAuthority() {
    return new GrantedAuthorityImpl(ROLE_PREFIX + this.name());
  }

  /** Return true if this role is in roles array */
  public boolean inArray(UserRole[] roles) {
    for (UserRole rol : roles) { if (this.equals(rol)) { return true; } }
    return false;
  }
}
