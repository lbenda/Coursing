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
import cz.lbenda.coursing.dto.Race;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.BeanInfo;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "DN_JudgeRootNode=Judges"
})
public class JudgeRootNode extends AbstractNode {

  private static final Logger LOG = LoggerFactory.getLogger(JudgeRootNode.class);

  private final Race race;

  public JudgeRootNode() {
    this(null, new InstanceContent());
  }

  public JudgeRootNode(Race race) {
    this(race, new InstanceContent());
  }

  public JudgeRootNode(Race race, InstanceContent ic) {
    super(Children.create(new JudgeChildFactory(race), true), new AbstractLookup(ic));
    this.setDisplayName(Bundle.DN_JudgeRootNode());
    this.race = race;
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Judge/Add");
    return actions.toArray(new Action[actions.size()]);
  }

  private static final Image smallColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/judge.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/judge32.png"))).getImage(); // NOI18N

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

  @Override
  public final PasteType getDropType(Transferable t, int action, int index) {
    DataFlavor[] flavors = t.getTransferDataFlavors();
    for (DataFlavor fl : flavors) {
      try {
        Object data = t.getTransferData(fl);
        if (data instanceof JudgeNode) {
          return new JudgePastType(race, (JudgeNode) data);
        }
      } catch (IOException ex) {
        LOG.error("IOException: " + ex.toString(), ex);
      } catch (UnsupportedFlavorException ex) {
        LOG.error("Unsuported flavor exception: " + ex.toString(), ex);
      }
    }
    return super.getDropType(t, action, index);
  }
}
