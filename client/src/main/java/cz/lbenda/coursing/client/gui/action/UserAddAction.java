/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.UserForm;
import cz.lbenda.coursing.user.UserService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Admin/User/Add",
        id = "cz.lbenda.coursing.client.gui.action.AddUserAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/user-add.png",
        displayName = "#CTL_AddUserAction"
)
@Messages("CTL_AddUserAction=Add new user")
public final class UserAddAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent ev) {
    UserForm.showDialog(ClientServiceLocator.getInstance().getBean(UserService.class).createNew());
  }
}
