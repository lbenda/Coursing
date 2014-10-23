/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.JudgeForm;
import cz.lbenda.coursing.service.JudgeService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Coursing/Judge/Add",
        id = "cz.lbenda.coursing.client.gui.action.JudgeAddAction"
)
@ActionRegistration(
        displayName = "#CTL_JudgeAddAction"
)
@Messages("CTL_JudgeAddAction=Add new judge")
public final class JudgeAddAction implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    JudgeService js = ClientServiceLocator.getInstance().getBean(JudgeService.class);
    JudgeForm.showDialog(js.createNew());
  }
}
