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

import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class UserNode extends BeanNode<User> {

  public UserNode(User bean) throws IntrospectionException {
    this(bean, new InstanceContent());
  }

  public UserNode(final User bean, InstanceContent ic) throws IntrospectionException {
    super(bean, Children.LEAF, new AbstractLookup(ic));
    setName(bean.getUsername());
    setShortDescription(bean.getFirstName() + " " + bean.getLastName());
    ic.add(bean);
  }

  @Override
  public Action getPreferredAction() {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/User/Node");
    for (Action action : actions) {
      if (cz.lbenda.coursing.client.gui.action.UserEditAction.class.getName().equals(action.getValue("injectable"))) {
        return action;
      }
    }
    return null;
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/User/Node");
    return actions.toArray(new Action[actions.size()]);
  }

  private static final Map<UserRole, Map<Integer, Image>> IMAGES;

  private static Image CREATE_IMAGE(String imageName) {
    return (new javax.swing.ImageIcon(UserNode.class.getResource("/cz/lbenda/coursing/client/icon/" + imageName + ".png"))).getImage();
  }

  static {
    IMAGES = new HashMap<>(UserRole.values().length);
    Map<Integer, Image> mi = new HashMap<>();
    IMAGES.put(UserRole.USER, mi);
    mi.put(BeanInfo.ICON_COLOR_16x16, CREATE_IMAGE("user")); // NOI18N
    mi.put(BeanInfo.ICON_COLOR_32x32, CREATE_IMAGE("user32")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_16x16, CREATE_IMAGE("user_m")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_32x32, CREATE_IMAGE("user_m32")); // NOI18N

    mi = new HashMap<>();
    IMAGES.put(UserRole.ADMIN, mi);
    mi.put(BeanInfo.ICON_COLOR_16x16, CREATE_IMAGE("user-admin")); // NOI18N
    mi.put(BeanInfo.ICON_COLOR_32x32, CREATE_IMAGE("user-admin32")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_16x16, CREATE_IMAGE("user-admin_m")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_32x32, CREATE_IMAGE("user-admin_m32")); // NOI18N

    mi = new HashMap<>();
    IMAGES.put(UserRole.GUEST, mi);
    mi.put(BeanInfo.ICON_COLOR_16x16, CREATE_IMAGE("user-guest")); // NOI18N
    mi.put(BeanInfo.ICON_COLOR_32x32, CREATE_IMAGE("user-guest32")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_16x16, CREATE_IMAGE("user-guest_m")); // NOI18N
    mi.put(BeanInfo.ICON_MONO_32x32, CREATE_IMAGE("user-guest_m32")); // NOI18N
  }

  @Override
  public final Image getIcon(int type) {
    if (UserRole.ADMIN.inArray(this.getBean().getRoles())) {
      return IMAGES.get(UserRole.ADMIN).get(type);
    } else if (!UserRole.USER.inArray(this.getBean().getRoles())) {
      return IMAGES.get(UserRole.GUEST).get(type);
    } else {
      return IMAGES.get(UserRole.USER).get(type);
    }
  }

  @Override
  public final Image getOpenedIcon(int type) {
    return getIcon(type);
  }
}
