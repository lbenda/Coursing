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

import java.awt.Image;
import java.beans.BeanInfo;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Root node for user
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages("DN_UserRootNode=Users")
public class UserRootNode extends AbstractNode {

  private static final Logger LOG = LoggerFactory.getLogger(UserRootNode.class);

  private UserChildFactory ucf;

  public UserRootNode() {
    this(new UserChildFactory(), new InstanceContent());
  }

  public UserRootNode(UserChildFactory ucf, InstanceContent ic) {
    super(Children.create(ucf, true), new AbstractLookup(ic));
    this.ucf = ucf;
    setName(Bundle.DN_UserRootNode());
    this.setDisplayName(Bundle.DN_UserRootNode());
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Admin/User/Add");
    return actions.toArray(new Action[actions.size()]);
  }

  public void close() {
    ucf.close();
  }

  public void open() {
    ucf.open();
  }
  private static final Image smallColor = (new javax.swing.ImageIcon(UserRootNode.class.getResource("/cz/lbenda/coursing/client/icon/users.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(UserRootNode.class.getResource("/cz/lbenda/coursing/client/icon/users32.png"))).getImage(); // NOI18N
  private static final Image smallMono = (new javax.swing.ImageIcon(UserRootNode.class.getResource("/cz/lbenda/coursing/client/icon/users_m.png"))).getImage(); // NOI18N
  private static final Image largeMono = (new javax.swing.ImageIcon(UserRootNode.class.getResource("/cz/lbenda/coursing/client/icon/users_m32.png"))).getImage(); // NOI18N

  @Override
  public final Image getIcon(int type) {
    switch (type) {
      case BeanInfo.ICON_COLOR_16x16:
        return smallColor;
      case BeanInfo.ICON_COLOR_32x32:
        return largeColor;
      case BeanInfo.ICON_MONO_16x16:
        return smallMono;
      case BeanInfo.ICON_MONO_32x32:
        return largeMono;
      default:
        return smallColor;
    }
  }

  @Override
  public final Image getOpenedIcon(int type) {
    return getIcon(type);
  }

}
