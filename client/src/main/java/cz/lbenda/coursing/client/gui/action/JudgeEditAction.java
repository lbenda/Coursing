/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.gui.JudgeForm;
import cz.lbenda.coursing.dto.Judge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Judge/Node",
        id = "cz.lbenda.coursing.client.gui.action.JudgeEditAction"
)
@ActionRegistration(
        iconBase = "cz/lbenda/coursing/client/icon/judge.png",
        displayName = "#CTL_JudgeEditAction"
)
@Messages("CTL_JudgeEditAction=Edit judge")
public final class JudgeEditAction implements ActionListener {

  private final Judge context;

  public JudgeEditAction(Judge context) {
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    JudgeForm.showDialog(context);
  }
}
