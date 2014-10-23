/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.DogPlacementForm;
import cz.lbenda.coursing.dto.DogPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/DogPlacement/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogPlacementEditAction"
)
@ActionRegistration(
        displayName = "#CTL_DogPlacementEditAction"
)
@Messages("CTL_DogPlacementEditAction=Edit dog placement")
public final class DogPlacementEditAction implements ActionListener {

  private final DogPlacement context;

  public DogPlacementEditAction(DogPlacement context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    DogPlacementForm.showDialog(context);
  }
}
