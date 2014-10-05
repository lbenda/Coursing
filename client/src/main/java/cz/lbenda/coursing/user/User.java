/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
