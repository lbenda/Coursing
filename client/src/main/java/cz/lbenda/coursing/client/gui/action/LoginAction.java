/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
