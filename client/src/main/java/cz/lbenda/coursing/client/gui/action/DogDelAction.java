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
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.service.DogService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ActionID(
        category = "Coursing/Dog/Node",
        id = "cz.lbenda.coursing.client.gui.action.DogDelAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/dog-remove.png",
        displayName = "#CTL_DogDelAction"
)
@ActionReference(path = "Toolbars/Coursing", position = 500)
@Messages("CTL_DogDelAction=Delte dog")
public final class DogDelAction implements ActionListener {

  private static final Logger LOG = LoggerFactory.getLogger(DogDelAction.class);

  private final Dog context;

  public DogDelAction(Dog context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    ClientServiceLocator.getInstance().getBean(DogService.class).delete(context);
  }
}
