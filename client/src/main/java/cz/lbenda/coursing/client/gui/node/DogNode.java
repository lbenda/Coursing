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

import cz.lbenda.coursing.client.gui.FrmJudgeTopComponent;
import cz.lbenda.coursing.dto.Dog;
import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.PropertiesAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogNode extends BeanNode<Dog> {

  public DogNode(final Dog dog) throws IntrospectionException {
    this(dog, new InstanceContent());
  }

  public DogNode(final Dog dog, final InstanceContent ic) throws IntrospectionException {
    super(dog, Children.LEAF, new AbstractLookup(ic));
    ic.add(dog);
  }

  @Override
  public Action getPreferredAction() {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Dog/Node");
    for (Action action : actions) {
      if (cz.lbenda.coursing.client.gui.action.DogEditAction.class.getName().equals(action.getValue("injectable"))) {
        return action;
      }
    }
    return null;
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Dog/Node");
    Action[] result = new Action[actions.size() + 1];
    result[0] = SystemAction.get(PropertiesAction.class);
    for (int i = 0; i < actions.size(); i++) {
      result[i + 1] = actions.get(i);
    }
    return result;
  }

  public Dog getDog() {
    return getBean();
  }

  private static final Image smallColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/dog.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/dog32.png"))).getImage(); // NOI18N

  @Override
  public final Image getIcon(int type) {
    switch (type) {
      case BeanInfo.ICON_MONO_16x16:
      case BeanInfo.ICON_COLOR_16x16:
        return smallColor;
      case BeanInfo.ICON_COLOR_32x32:
      case BeanInfo.ICON_MONO_32x32:
        return largeColor;
      default:
        return smallColor;
    }
  }

  @Override
  public final Image getOpenedIcon(int type) {
    return getIcon(type);
  }
}
