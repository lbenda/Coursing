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
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.service.JudgeService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Judge/Node",
        id = "cz.lbenda.coursing.client.gui.action.JudgeDelCookie"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/judge-remove.png",
        displayName = "#CTL_JudgeDelCookie"
)
@ActionReference(path = "Menu/File", position = -100)
@Messages({
  "CTL_JudgeDelCookie=Remove judge",
  "DLG_JudgeDelAction_Ask=Will be judge %s %s delted?",
  "DLG_JudgeDelAction_Title=Remove judge"
})
public final class JudgeDelAction implements ActionListener {

  private final Judge context;

  public JudgeDelAction(Judge context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    NotifyDescriptor d = new NotifyDescriptor.Confirmation(
            String.format(Bundle.DLG_JudgeDelAction_Ask(), context.getFirstName(), context.getLastName()),
            Bundle.DLG_JudgeDelAction_Title(), NotifyDescriptor.OK_CANCEL_OPTION);
    if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
      JudgeService judgeService = ClientServiceLocator.getInstance().getBean(JudgeService.class);
      judgeService.delete(context);
    }
  }
}
