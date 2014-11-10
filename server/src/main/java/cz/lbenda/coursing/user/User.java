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

import cz.lbenda.coursing.dto.DTO;
import java.util.Date;

/**
 *
 * @author benzin
 */
public interface User extends DTO {

  String getFirstName();
  void setFirstName(String firstName);

  String getLastName();
  void setLastName(String lastName);

  String getUsername();
  void setUsername(String username);

  String getPasswd();
  void setPasswd(String passwd);

  Date getValidFrom();
  void setValidFrom(Date validFrom);

  Date getValidTo();
  void setValidTo(Date validTo);

  boolean isLocked();
  void setLocked(boolean locked);

  UserRole[] getRoles();
  void setRoles(UserRole[] roles);

  void addRole(UserRole role);
  void removeRole(UserRole role);
}
