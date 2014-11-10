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
package cz.lbenda.coursing.server.user;

import cz.lbenda.coursing.server.dto.DTOImpl;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author  * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Entity(name = "AppUser")
public class UserImpl extends DTOImpl implements User {

  @Column(length=50)
  private String firstName;
  @Column(length=50)
  private String lastName;
  @Column(length=60, unique = true, nullable = false)
  private String username;
  @Column(length=60, columnDefinition="CHAR(60)", nullable = false)
  private String passwd;
  @Column
  @Temporal(TIMESTAMP)
  private Date validFrom;
  @Column
  @Temporal(TIMESTAMP)
  private Date validTo;
  @Column(length = 1000, nullable = false)
  private String userRole;
  @Column
  private Boolean locked;

  public @Override String getFirstName() {
        return firstName;
    }
  public @Override void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  public @Override String getLastName() {
        return lastName;
    }
  public @Override void setLastName(String lastName) {
        this.lastName = lastName;
    }

  public @Override String getUsername() {
        return username;
    }
  public @Override void setUsername(String username) { this.username = username; }

  public @Override Date getValidFrom() {
        return validFrom;
    }
  public @Override void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

  public @Override Date getValidTo() {
        return validTo;
    }
  public @Override void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

  public String getUserRole() {
    return userRole;
  }
  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  @Override
  public UserRole[] getRoles() {
    return UserRole.toArray(getUserRole());
  }
  @Override
  public void setRoles(UserRole[] roles) {
    setUserRole(UserRole.toString(roles));
  }
  @Override
  public void addRole(UserRole role) {
    if (this.userRole == null || "".equals(userRole.trim())) { userRole = role.name(); }
    else { userRole += "," + role.name(); }
    setUserRole(userRole);
  }
  @Override
  public void removeRole(UserRole role) {
    List<UserRole> roles = Arrays.asList(UserRole.toArray(userRole));
    roles.remove(role);
    setRoles(roles.toArray(new UserRole[roles.size()]));
  }

  public @Override String getPasswd() { return passwd; }
  public @Override void setPasswd(String passwd) { this.passwd = passwd; }

  public @Override boolean isLocked() { return locked != null && locked.booleanValue(); }
  public @Override void setLocked(boolean locked) { this.locked = Boolean.valueOf(locked); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserImpl dto = (UserImpl) o;

    if (getId() != null ? !getId().equals(dto.getId()) : dto.getId() != null) return false;
    if (getVersion() != null ? !getVersion().equals(dto.getVersion()) : dto.getVersion() != null) return false;
    if (username != null ? !username.equals(dto.getUsername()) : dto.getUsername() != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
    result = 37 * result + (username != null ? username.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserImpl{" +
        "id='" + getId() + '\'' +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", username='" + username + '\'' +
        ", passwd='" + passwd + '\'' +
        ", validFrom=" + validFrom +
        ", validTo=" + validTo +
        ", userRole='" + userRole + '\'' +
        ", locked=" + locked +
        ", version=" + getVersion() +
        '}';
  }
}
