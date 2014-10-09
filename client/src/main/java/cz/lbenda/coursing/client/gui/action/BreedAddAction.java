/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.service.BreedService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Breed",
        id = "cz.lbenda.coursing.client.gui.action.BreedAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/breed-add.png",
        displayName = "#CTL_BreedAddAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 500)
@Messages("CTL_BreedAddAction=Add new breed type")
public final class BreedAddAction implements ActionListener {

  private final BreedAddCookie context;

  public BreedAddAction(BreedAddCookie context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    BreedService bs = ClientServiceLocator.getInstance().getBean(BreedService.class);
    bs.createNew();
  }
}
