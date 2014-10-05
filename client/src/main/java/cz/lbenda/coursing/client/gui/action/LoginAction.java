/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "cz.lbenda.coursing.gui.action.LoginAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/gui/action/login16x16.png",
        displayName = "#CTL_LoginAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 0),
    @ActionReference(path = "Toolbars/File", position = 0)
})
@Messages("CTL_LoginAction=Login")
public final class LoginAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    LoginForm.showLoginDialog();
  }
}
