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
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ActionID(
        category = "Coursing/DTO/Add",
        id = "cz.lbenda.coursing.client.gui.action.NumberLineAddAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/list-add.png",
        displayName = "#CTL_NumberLineAddAction"
)
@Messages("CTL_NumberLineAddAction=Add new number line type")
public final class DTOAddAction implements ActionListener {

  private static Logger LOG = LoggerFactory.getLogger(DTOAddAction.class);

  private final DTOAddCookie context;

  public DTOAddAction(DTOAddCookie context) {
    this.context = (DTOAddCookie) context;
    LOG.debug("Number line add action is create with context: " + context);
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    context.showForm(context.numberLineService().createNew());
  }
}
