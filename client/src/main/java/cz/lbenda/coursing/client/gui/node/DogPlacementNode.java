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
import cz.lbenda.coursing.dto.DogPlacement;
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

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogPlacementNode extends BeanNode<DogPlacement> {
  public DogPlacementNode(DogPlacement bean) throws IntrospectionException {
    this(bean, new InstanceContent());
  }
  public DogPlacementNode(DogPlacement bean, InstanceContent ic) throws IntrospectionException {
    super(bean, Children.LEAF, new AbstractLookup(ic));
    setDisplayName(bean.getDog().getName());
    ic.add(bean);
  }

  @Override
  public Action getPreferredAction() {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/DogPlacement/Node");
    for (Action action : actions) {
      if (cz.lbenda.coursing.client.gui.action.DogPlacementEditAction.class.getName().equals(action.getValue("injectable"))) {
        return action;
      }
    }
    return null;
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/DogPlacement/Node");
    return actions.toArray(new Action[actions.size()]);
  }

  private static final Image[][] COLORS = new Image[][] {
          {
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-1.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-132.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-1-mono.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-1-mono32.png"))).getImage() // NOI18N
          },{
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-2.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-232.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-2-mono.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-2-mono32.png"))).getImage() // NOI18N
          },{
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-3.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-332.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-3-mono.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award-3-mono32.png"))).getImage() // NOI18N
          },{
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award32.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award.png"))).getImage(), // NOI18N
            (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/award/award32.png"))).getImage() // NOI18N
          }
  };

  @Override
  public final Image getIcon(int type) {
    int i = 3;
    if (getBean().getPlacementTotal() != null) { i = getBean().getPlacementTotal() - 1; }
    if (i < 0) { i = 0; }
    if (i > 3) { i = 3; }
    switch (type) {
      case BeanInfo.ICON_MONO_16x16: return COLORS[i][2];
      case BeanInfo.ICON_COLOR_16x16: return COLORS[i][0];
      case BeanInfo.ICON_COLOR_32x32: return COLORS[i][1];
      case BeanInfo.ICON_MONO_32x32: return COLORS[i][3];
      default: return COLORS[i][0];
    }
  }

  @Override
  public final Image getOpenedIcon(int type) {
    return getIcon(type);
  }
}
