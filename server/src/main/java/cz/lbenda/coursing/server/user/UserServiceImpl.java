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
package cz.lbenda.coursing.server.user;

import cz.lbenda.coursing.server.AbstractDTOServiceImpl;
import cz.lbenda.coursing.server.security.UserDetailsImpl;
import cz.lbenda.coursing.service.AbstractPasswordGenerator;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import cz.lbenda.coursing.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
// @Service("UserService")
public final class UserServiceImpl extends AbstractDTOServiceImpl<User> implements UserService, UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  public final static String FIRST_USERNAME = "sys";
  public final static String FIRST_PASSWORD = AbstractPasswordGenerator.generatePassword("sys");

  @Autowired
  private UserRepository repository;

  @Override
  protected JpaRepository<User, String> repository() {
    return (JpaRepository<User, String>) (Object) repository;
  }

  @Override
  @Transactional(readOnly = true)
  public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserImpl user = repository.findByUsername(username);
    if (user == null && FIRST_USERNAME.equals(username) && count() == 0) { // This is check if database is void, then first admin user is created
      user = firstAdmin();
      LOG.info("The database is void so new user si created");
    }
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserDetailsImpl(user);
  }

  /** Create first user which have Admin right. But only when no any othere user is in databse. */
  public final UserImpl firstAdmin() {
    UserImpl ui = new UserImpl();
    ui.setFirstName("Default");
    ui.setLastName("Default");
    ui.setLocked(false);
    LOG.trace("first username: " + FIRST_USERNAME);
    ui.setUsername(FIRST_USERNAME);
    LOG.trace("First password: " + FIRST_PASSWORD);
    ui.setPasswd(FIRST_PASSWORD);
    ui.setLocked(false);
    ui.addRole(UserRole.ADMIN);
    ui.addRole(UserRole.USER);
    return ui;
  }

  @Override
  public User createNew() throws UnsupportedOperationException {
    UserImpl ui = new UserImpl();
    ui.addRole(UserRole.USER);
    ui.setLocked(false);
    return ui;
  }
}
