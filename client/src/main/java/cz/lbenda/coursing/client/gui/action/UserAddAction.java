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

@ActionID(
        category = "Coursing/Admin/User",
        id = "cz.lbenda.coursing.client.gui.action.AddUserAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/user-add.png",
        displayName = "#CTL_AddUserAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 100)
@Messages("CTL_AddUserAction=Add new user")
public final class UserAddAction implements ActionListener {

  private final UserAddCookie context;

  public UserAddAction(UserAddCookie context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    context.userAdd(ev);
  }
}
