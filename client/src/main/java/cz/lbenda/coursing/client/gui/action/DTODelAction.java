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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/DTO/Del",
        id = "cz.lbenda.coursing.client.gui.action.BreedDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/list-remove.png",
        displayName = "#CTL_BreedDelAction"
)
@Messages("CTL_BreedDelAction=Remove selected breed")
public final class DTODelAction implements ActionListener {

  private final List<DTODelCookie> context;

  public DTODelAction(List<DTODelCookie> context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    for (DTODelCookie numberLineDelCookie : context) {
      numberLineDelCookie.numberLineService().delete(numberLineDelCookie.removedNumberLine());
    }
  }
}
