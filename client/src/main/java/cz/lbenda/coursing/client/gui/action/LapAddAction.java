/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Lap;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.LapService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Race/Node",
        id = "cz.lbenda.coursing.client.gui.action.LapAddAction"
)
@ActionRegistration(
        displayName = "#CTL_LapAddAction"
)
@Messages("CTL_LapAddAction=Create new lap")
public final class LapAddAction implements ActionListener {

  private final Race context;

  public LapAddAction(Race context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    LapService ls = ClientServiceLocator.getInstance().getBean(LapService.class);
    Lap lap = ls.createNew();
    context.getLaps().add(lap);
  }
}
