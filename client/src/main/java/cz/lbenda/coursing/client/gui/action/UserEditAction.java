/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.UserForm;
import cz.lbenda.coursing.user.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/User/Node",
        id = "cz.lbenda.coursing.client.gui.action.UserEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/user.png",
        displayName = "#CTL_UserEditAction"
)
@Messages("CTL_UserEditAction=Edit user")
public final class UserEditAction implements ActionListener {

  private final User context;

  public UserEditAction(User context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    UserForm.showDialog(context);
  }
}
