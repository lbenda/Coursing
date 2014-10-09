/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ActionID(
        category = "Coursing/Dog",
        id = "cz.lbenda.coursing.client.gui.action.DogAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dog-add.png",
        displayName = "#CTL_DogAddAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 0)
@Messages("CTL_DogAddAction=Add new DOG")
public final class DogAddAction implements ActionListener {

  private static final Logger LOG = LoggerFactory.getLogger(DogAddAction.class);

  private final DogAddCookie context;

  public DogAddAction(DogAddCookie context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    context.addNewDog(ev);
  }
}
