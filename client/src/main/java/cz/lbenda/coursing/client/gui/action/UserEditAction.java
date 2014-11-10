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
