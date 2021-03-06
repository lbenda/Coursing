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
package cz.lbenda.coursing.client.gui;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.service.SecurityService;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

/** Form for login user
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "CTL_LoginForm=Login"
})
public class LoginForm extends javax.swing.JPanel {

  private static final Logger LOG = LoggerFactory.getLogger(LoginForm.class);

  private static final Image LOGIN_ICON = (new javax.swing.ImageIcon(FrmJudgeTopComponent.class.getResource("/cz/lbenda/coursing/client/icon/login128.png"))).getImage(); // NOI18N

  /**
   * Creates new form LoginForm
   */
  public LoginForm() {
    initComponents();
  }

  public String getUsername() {
    if (tfUsername == null) { return null; }
    return tfUsername.getText().trim();
  }

  public String getPassword() {
    if (pfPassword== null) { return null; }
    return String.valueOf(pfPassword.getPassword()).trim();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    lUsername = new javax.swing.JLabel();
    lPassword = new javax.swing.JLabel();
    tfUsername = new javax.swing.JTextField();
    pfPassword = new javax.swing.JPasswordField();

    org.openide.awt.Mnemonics.setLocalizedText(lUsername, org.openide.util.NbBundle.getMessage(LoginForm.class, "LoginForm.lUsername.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lPassword, org.openide.util.NbBundle.getMessage(LoginForm.class, "LoginForm.lPassword.text")); // NOI18N

    tfUsername.setText(org.openide.util.NbBundle.getMessage(LoginForm.class, "LoginForm.tfUsername.text")); // NOI18N

    pfPassword.setText(org.openide.util.NbBundle.getMessage(LoginForm.class, "LoginForm.pfPassword.text")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lUsername)
          .addComponent(lPassword))
        .addGap(9, 9, 9)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(pfPassword)
          .addComponent(tfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lUsername)
          .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lPassword)
          .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel lPassword;
  private javax.swing.JLabel lUsername;
  private javax.swing.JPasswordField pfPassword;
  private javax.swing.JTextField tfUsername;
  // End of variables declaration//GEN-END:variables

  public static void showLoginDialog() {
    final LoginForm form = new LoginForm();
    JButton login = new JButton();
    login.setText("Login"); // FIXME localizable
    JButton cancel = new JButton();
    cancel.setText("Cancel"); // FIXME localizable

    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        exit();
      }
    });

    login.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          SecurityService ss = (SecurityService) ClientServiceLocator.getInstance().getBean(SecurityService.SERVICE_NAME);
          ss.login(form.getUsername(), form.getPassword());
        } catch (AuthenticationException ex) {
          NotifyDescriptor info = new NotifyDescriptor.Message(ex.getLocalizedMessage(),
                  NotifyDescriptor.INFORMATION_MESSAGE);
          DialogDisplayer.getDefault().notify(info);
          LoginForm.showLoginDialog();
        }
      }
    });

    NotifyDescriptor nd = new NotifyDescriptor.Confirmation(form, Bundle.CTL_LoginForm());
    nd.setOptions(new Object[] { login, cancel });
    nd.addPropertyChangeListener(new PropertyChangeListener(){
      @Override
      public void propertyChange(PropertyChangeEvent evt){
        if(NotifyDescriptor.CLOSED_OPTION.equals(evt.getNewValue())){
          exit();
        }
      }
    });
    DialogDisplayer.getDefault().notifyLater(nd);
  }

  private static void exit() {
    LifecycleManager.getDefault().exit();
  }
}
