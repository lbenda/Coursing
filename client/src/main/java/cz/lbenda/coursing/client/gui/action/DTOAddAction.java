/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ActionID(
        category = "Coursing/DTO/Add",
        id = "cz.lbenda.coursing.client.gui.action.NumberLineAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/list-add.png",
        displayName = "#CTL_NumberLineAddAction"
)
@Messages("CTL_NumberLineAddAction=Add new number line type")
public final class DTOAddAction implements ActionListener {

  private static Logger LOG = LoggerFactory.getLogger(DTOAddAction.class);

  private final DTOAddCookie context;

  public DTOAddAction(DTOAddCookie context) {
    this.context = (DTOAddCookie) context;
    LOG.debug("Number line add action is create with context: " + context);
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    context.showForm(context.numberLineService().createNew());
  }
}
