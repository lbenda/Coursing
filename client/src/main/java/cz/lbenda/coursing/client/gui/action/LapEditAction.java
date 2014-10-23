/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.LapForm;
import cz.lbenda.coursing.dto.Lap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Lap/Node",
        id = "cz.lbenda.coursing.client.gui.action.LapEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/lap.png",
        displayName = "#CTL_LapEditAction"
)
@Messages("CTL_LapEditAction=Lap edit")
public final class LapEditAction implements ActionListener {

  private final Lap context;

  public LapEditAction(Lap context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    LapForm.showDialog(context);
  }
}
