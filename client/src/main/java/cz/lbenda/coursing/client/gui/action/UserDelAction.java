/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/User/Node",
        id = "cz.lbenda.coursing.client.gui.action.UserDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/user-remove.png",
        displayName = "#CTL_UserDelAction"
)
@Messages({
  "CTL_UserDelAction=Remove user",
  "DLG_UserDelAction_Title=Deleting user",
  "DLG_userDelAction_Ask=Do you want remove user %s"
})
public final class UserDelAction implements ActionListener {

  private final User context;

  public UserDelAction(User context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
     NotifyDescriptor d = new NotifyDescriptor.Confirmation(
             String.format(Bundle.DLG_userDelAction_Ask(), context.getUsername()),
             Bundle.DLG_UserDelAction_Title(), NotifyDescriptor.OK_CANCEL_OPTION);
     if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
       ClientServiceLocator.getInstance().getBean(UserService.class).delete(context);
     }
  }
}
