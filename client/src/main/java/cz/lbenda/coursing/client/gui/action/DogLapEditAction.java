/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.DogLapForm;
import cz.lbenda.coursing.dto.DogLap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/DogLap/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogLapEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dogLap.png",
        displayName = "#CTL_DogLapEditAction"
)
@Messages("CTL_DogLapEditAction=Edit dog lap")
public final class DogLapEditAction implements ActionListener {

  private final DogLap context;

  public DogLapEditAction(DogLap context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    DogLapForm.showDialog(context);
  }
}
