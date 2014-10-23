/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.DogForm;
import cz.lbenda.coursing.dto.Dog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Dog/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dog.png",
        displayName = "#CTL_DogEditAction"
)
@Messages("CTL_DogEditAction=Edit dog")
public final class DogEditAction implements ActionListener {

  private final Dog context;

  public DogEditAction(Dog context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    DogForm.showDialog(context);
  }
}
