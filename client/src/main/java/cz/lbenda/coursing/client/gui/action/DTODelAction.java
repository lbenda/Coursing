/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/DTO/Del",
        id = "cz.lbenda.coursing.client.gui.action.BreedDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/list-remove.png",
        displayName = "#CTL_BreedDelAction"
)
@Messages("CTL_BreedDelAction=Remove selected breed")
public final class DTODelAction implements ActionListener {

  private final List<DTODelCookie> context;

  public DTODelAction(List<DTODelCookie> context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    for (DTODelCookie numberLineDelCookie : context) {
      numberLineDelCookie.numberLineService().delete(numberLineDelCookie.removedNumberLine());
    }
  }
}
