/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.dto.DogLap;
import cz.lbenda.coursing.dto.DogPlacement;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.DogLapService;
import cz.lbenda.coursing.service.DogPlacementService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/DogLap/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogLapDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dogLap-remove.png",
        displayName = "#CTL_DogLapDelAction"
)
@Messages({
  "CTL_DogLapDelAction=Remove dog from lap",
  "DLG_DogLapDelAction_Ask=Realy remove dog %s from lap?",
  "DLG_DogLapDelAction_Title=Remove dog lap"
})
public final class DogLapDelAction implements ActionListener {

  private final DogLap context;

  public DogLapDelAction(DogLap context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    NotifyDescriptor d = new NotifyDescriptor.Confirmation(
            String.format(Bundle.DLG_DogLapDelAction_Ask(), context.getDog().getName()),
            Bundle.DLG_DogLapDelAction_Title(), NotifyDescriptor.OK_CANCEL_OPTION);
    if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
      context.getLap().getDogLaps().remove(context);
      Race race = context.getLap().getRace();
      Dog dog = context.getDog();
      for (Iterator<DogPlacement> itt = race.getDogPlacements().iterator(); itt.hasNext(); ) {
        DogPlacement dp = itt.next();
        if (dp.getDog().equals(dog)) {
          itt.remove();
          ClientServiceLocator.getInstance().getBean(DogPlacementService.class).delete(dp);
        }
      }
      ClientServiceLocator.getInstance().getBean(DogLapService.class).delete(context);
    }
  }
}
