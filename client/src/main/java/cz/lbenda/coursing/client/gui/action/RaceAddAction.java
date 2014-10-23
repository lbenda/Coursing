/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.RaceForm;
import cz.lbenda.coursing.service.RaceService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Race/Add",
        id = "cz.lbenda.coursing.client.gui.action.RaceAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/race-add.png",
        displayName = "#CTL_RaceAddAction"
)
@Messages("CTL_RaceAddAction=Add new Race")
public final class RaceAddAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    RaceService raceService = ClientServiceLocator.getInstance().getBean(RaceService.class);
    RaceForm.showDialog(raceService.createNew(), raceService);
  }
}
