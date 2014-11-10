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
import cz.lbenda.coursing.service.SecurityService;
import cz.lbenda.coursing.service.SecurityService.SecurityListener;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/** Login and logout child factory
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "NN_AbstractChildFactory_NoLogged=No user logged to system"
})
public abstract class AbstractChildFactory<T> extends ChildFactory<T> implements SecurityListener {

  protected SecurityService securityService = ClientServiceLocator.getInstance().getBean(SecurityService.class);

  public AbstractChildFactory() {
    securityService.addSecurityListener(this);
  }

  public boolean isAuthenticated() {
    return securityService.getCurrentUser() != null;
  }

  @Override
  protected final boolean createKeys(List<T> toPopulate) {
    if (!isAuthenticated()) {
      toPopulate.clear();
      toPopulate.add((T) new UserNotLogedNode());
      return true;
    }
    return generateKeys(toPopulate);
  }

  protected abstract boolean generateKeys(List<T> toPopulate);
  protected abstract Node createNode(T key);

  @Override
  protected final Node createNodeForKey(T key) {
    if (key instanceof UserNotLogedNode) { return (Node) key; }
    return createNode(key);
  }

  @Override
  public final void onLogin() {
    refresh(true);
  }
  @Override
  public final void onLogout() {
    refresh(true);
  }

  private static class UserNotLogedNode extends AbstractNode {
    public UserNotLogedNode() {
      super(Children.LEAF);
      setName(Bundle.NN_AbstractChildFactory_NoLogged());
    }

    @Override
    public Action getPreferredAction() {
      List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Login");
      return actions.get(0);
    }
  }
}
