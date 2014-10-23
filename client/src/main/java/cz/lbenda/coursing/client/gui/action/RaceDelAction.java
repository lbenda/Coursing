/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.RaceService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Race/Node",
        id = "cz.lbenda.coursing.client.gui.action.RaceDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/race-del.png",
        displayName = "#CTL_RaceDelAction"
)
@Messages({"CTL_RaceDelAction=Remove race",
  "DLG_RaceDelAction_Title=Remove race",
  "DLG_RaceDelAction_Ask=Remove race: %s",
})
public final class RaceDelAction implements ActionListener {

  private final Race context;

  public RaceDelAction(Race context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    NotifyDescriptor d = new NotifyDescriptor.Confirmation(
             String.format(Bundle.DLG_RaceDelAction_Ask(), context.getName()),
             Bundle.DLG_RaceDelAction_Title(), NotifyDescriptor.OK_CANCEL_OPTION);
     if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
       ClientServiceLocator.getInstance().getBean(RaceService.class).delete(context);
     }
  }
}
