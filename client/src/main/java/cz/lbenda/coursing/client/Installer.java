/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client;

import cz.lbenda.coursing.client.gui.LoginForm;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

  @Override
  public void restored() {
    LoginForm.showLoginDialog();
  }

}
