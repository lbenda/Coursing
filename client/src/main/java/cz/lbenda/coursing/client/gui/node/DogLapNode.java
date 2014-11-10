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

import cz.lbenda.coursing.dto.DogLap;
import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogLapNode extends BeanNode<DogLap> {

  private static Logger LOG = LoggerFactory.getLogger(DogLapNode.class);

  public DogLapNode(DogLap bean) throws IntrospectionException {
    this(bean, new InstanceContent());
  }

  public DogLapNode(final DogLap bean, InstanceContent ic) throws IntrospectionException {
    super(bean, Children.LEAF, new AbstractLookup(ic));
    setDisplayName(bean.getDog().getName());
    ic.add(bean);
  }

  @Override
  public Action getPreferredAction() {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/DogLap/Node");
    for (Action action : actions) {
      if (cz.lbenda.coursing.client.gui.action.DogLapEditAction.class.getName().equals(action.getValue("injectable"))) {
        return action;
      }
    }
    return null;
  }
  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/DogLap/Node");
    return actions.toArray(new Action[actions.size()]);
  }


  private static final Image smallColor = (new javax.swing.ImageIcon(DogLapNode.class.getResource("/cz/lbenda/coursing/client/icon/dogLap.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(DogLapNode.class.getResource("/cz/lbenda/coursing/client/icon/dogLap32.png"))).getImage(); // NOI18N

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
