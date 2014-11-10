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
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.server.security.UserDetailsImpl;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class UserChildFactory extends AbstractChildFactory<User> implements AbstractDTOService.DTOChangedListener<User> {
  private final UserService userService = ClientServiceLocator.getInstance().getBean(UserService.class);

  private static final Logger LOG = LoggerFactory.getLogger(UserChildFactory.class);

  public UserChildFactory() {
    super();
    userService.addDTOChangedListener(this);
  }

  public void close() {
    userService.removeDTOChangedListener(this);
  }

  public void open() {
    userService.addDTOChangedListener(this);
    refresh(true);
  }

  @Override
  protected boolean generateKeys(List<User> toPopulate) {
    toPopulate.addAll(userService.allEntities());
    if (toPopulate.isEmpty()) {
      toPopulate.add(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
    }
    return true;
  }

  @Override
  protected Node createNode(User key) {
    LOG.debug("create node for key: " + key);
    try {
      return new UserNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Problem with creating race node", ex);
      throw new RuntimeException("Problem with creating race node", ex);
    }
  }

  @Override
  public void dtoChanged(User oldDTO, User newDTO) {
    if (oldDTO == null) {
      this.refresh(true);
    }
  }
}
