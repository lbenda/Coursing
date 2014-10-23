/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.service.JudgeService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Judge/Node",
        id = "cz.lbenda.coursing.client.gui.action.JudgeDelCookie"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/judge-remove.png",
        displayName = "#CTL_JudgeDelCookie"
)
@ActionReference(path = "Menu/File", position = -100)
@Messages({
  "CTL_JudgeDelCookie=Remove judge",
  "DLG_JudgeDelAction_Ask=Will be judge %s %s delted?",
  "DLG_JudgeDelAction_Title=Remove judge"
})
public final class JudgeDelAction implements ActionListener {

  private final Judge context;

  public JudgeDelAction(Judge context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    NotifyDescriptor d = new NotifyDescriptor.Confirmation(
            String.format(Bundle.DLG_JudgeDelAction_Ask(), context.getFirstName(), context.getLastName()),
            Bundle.DLG_JudgeDelAction_Title(), NotifyDescriptor.OK_CANCEL_OPTION);
    if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
      JudgeService judgeService = ClientServiceLocator.getInstance().getBean(JudgeService.class);
      judgeService.delete(context);
    }
  }
}
