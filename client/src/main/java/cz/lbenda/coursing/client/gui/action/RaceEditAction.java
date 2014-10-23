/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.RaceForm;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.RaceService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Race/Node",
        id = "cz.lbenda.coursing.client.gui.action.RaceEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/race.png",
        displayName = "#CTL_RaceEditAction"
)
@Messages("CTL_RaceEditAction=Edit race")
public final class RaceEditAction implements ActionListener {

  private final Race context;

  public RaceEditAction(Race context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    RaceService rs = ClientServiceLocator.getInstance().getBean(RaceService.class);
    RaceForm.showDialog(context, rs);
  }
}
