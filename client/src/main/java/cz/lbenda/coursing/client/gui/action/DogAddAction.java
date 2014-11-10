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
import cz.lbenda.coursing.client.gui.DogForm;
import cz.lbenda.coursing.service.DogService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Dog",
        id = "cz.lbenda.coursing.client.gui.action.DogAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dog-add.png",
        displayName = "#CTL_DogAddAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 300)
@Messages("CTL_DogAddAction=Add new DOG")
public final class DogAddAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent ev) {
    DogService dogService = ClientServiceLocator.getInstance().getBean(DogService.class);
    DogForm.showDialog(dogService.createNew());
  }
}
