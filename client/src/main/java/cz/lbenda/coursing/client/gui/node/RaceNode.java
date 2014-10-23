/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.FrmJudgeTopComponent;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.RaceService;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
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
public class RaceNode extends BeanNode<Race> {

  private static final Logger LOG = LoggerFactory.getLogger(RaceNode.class);

  public RaceNode(Race bean) throws IntrospectionException {
    this(bean, new InstanceContent());
  }

  public RaceNode(final Race bean, InstanceContent ic) throws IntrospectionException {
    super(bean, Children.create(new DefaultChildFactory(new JudgeRootNode(bean), new LapRootNode(bean),
            new DogPlacementRootNode(bean)), true), new AbstractLookup(ic));
    ic.add(bean);
  }

  private void save() {
    RaceService rs = ClientServiceLocator.getInstance().getBean(RaceService.class);
    rs.save(getBean());
  }

  @Override
  public Action getPreferredAction() {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Race/Node");
    for (Action action : actions) {
      if (cz.lbenda.coursing.client.gui.action.RaceEditAction.class.getName().equals(action.getValue("injectable"))) {
        return action;
      }
    }
    return null;
  }

  @Override
  public Action[] getActions(boolean context) {
    List<? extends Action> actions = Utilities.actionsForPath("Actions/Coursing/Race/Node");
    return actions.toArray(new Action[actions.size()]);
  }

  @Override
  public final PasteType getDropType(Transferable t, int action, int index) {
    DataFlavor[] flavors = t.getTransferDataFlavors();
    for (DataFlavor fl : flavors) {
      try {
        Object data = t.getTransferData(fl);
        if (data instanceof JudgeNode) {
          return new JudgePastType(getBean(), (JudgeNode) data);
        } else if (data instanceof DogNode) {
          return new DogPastType(getBean(), (DogNode) data);
        }
      } catch (IOException ex) {
        LOG.error("IOException: " + ex.toString(), ex);
      } catch (UnsupportedFlavorException ex) {
        LOG.error("Unsuported flavor exception: " + ex.toString(), ex);
      }
    }
    LOG.debug("action " + action + " index: " + index + " Transferable: " + t);
    return super.getDropType(t, action, index);
  }

  private static final Image smallColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/race.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/race32.png"))).getImage(); // NOI18N

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
