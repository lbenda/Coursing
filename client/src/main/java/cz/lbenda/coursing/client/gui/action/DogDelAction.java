/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.service.DogService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ActionID(
        category = "Coursing/Dog/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dog-remove.png",
        displayName = "#CTL_DogDelAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 500)
@Messages("CTL_DogDelAction=Delte dog")
public final class DogDelAction implements ActionListener {

  private static final Logger LOG = LoggerFactory.getLogger(DogDelAction.class);

  private final Dog context;

  public DogDelAction(Dog context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    ClientServiceLocator.getInstance().getBean(DogService.class).delete(context);
  }
}
