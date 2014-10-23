/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.dto.Race;
import java.awt.Image;
import java.beans.BeanInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "DN_DogPlacementRootNode=Placements"
})
public class DogPlacementRootNode extends AbstractNode {

  public DogPlacementRootNode(Race race) {
    this(race, new InstanceContent());
  }

  public DogPlacementRootNode(Race race, InstanceContent ic) {
    super(Children.create(new DogPlacementChildFactory(race), true), new AbstractLookup(ic));
    setDisplayName(Bundle.DN_DogPlacementRootNode());
  }

  private static final Image smallColor = (new javax.swing.ImageIcon(DogPlacementRootNode.class.getResource("/cz/lbenda/coursing/client/icon/award/award.png"))).getImage(); // NOI18N
  private static final Image largeColor = (new javax.swing.ImageIcon(DogPlacementRootNode.class.getResource("/cz/lbenda/coursing/client/icon/award/award.png"))).getImage(); // NOI18N

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
