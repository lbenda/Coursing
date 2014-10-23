/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.LoginForm;
import cz.lbenda.coursing.service.SecurityService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Login",
        id = "cz.lbenda.coursing.gui.action.LoginAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/login.png",
        displayName = "#CTL_LoginAction"
)
@ActionReferences(@ActionReference(path = "Toolbars/Coursing", position = 0))
@Messages("CTL_LoginAction=Login")
public final class LoginAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    SecurityService ss = ClientServiceLocator.getInstance().getBean(SecurityService.class);
    ss.logout();
    LoginForm.showLoginDialog();
  }
}
