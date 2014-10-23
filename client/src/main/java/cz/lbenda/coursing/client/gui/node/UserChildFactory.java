/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
