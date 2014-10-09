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
import java.util.List;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Breed",
        id = "cz.lbenda.coursing.client.gui.action.BreedDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/breed-remove.png",
        displayName = "#CTL_BreedDelAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 700)
@Messages("CTL_BreedDelAction=Remove selected breed")
public final class BreedDelAction implements ActionListener {

  private final List<BreedDelCookie> context;

  public BreedDelAction(List<BreedDelCookie> context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    BreedService bs = ClientServiceLocator.getInstance().getBean(BreedService.class);
    for (BreedDelCookie breedDelCookie : context) {
      bs.delete(breedDelCookie.removedBreed());
    }
  }
}
