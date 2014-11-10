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
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import cz.lbenda.coursing.user.UserService;
import cz.lbenda.gui.JCheckBoxList;
import java.awt.BorderLayout;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle.Messages;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "DLG_UserForm_Save=User"
})
public class UserForm extends javax.swing.JPanel implements Customizer {

  private User user;
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private DTOPanel dtoPanel;

  /**
   * Creates new form UserCustomizer
   */
  public UserForm() {
    initComponents();
    initComponentsDTO();
    initRolesList();
  }

  private void initComponentsDTO() {
    pDTO.setLayout(new BorderLayout());
    dtoPanel = new DTOPanel();
    pDTO.add(dtoPanel, BorderLayout.CENTER);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();
    lUsername = new javax.swing.JLabel();
    lFirstName = new javax.swing.JLabel();
    lSurname = new javax.swing.JLabel();
    lValidFrom = new javax.swing.JLabel();
    lValidTo = new javax.swing.JLabel();
    lRoles = new javax.swing.JLabel();
    btnChagngePassword = new javax.swing.JButton();
    tfUsername = new javax.swing.JTextField();
    tfFirstname = new javax.swing.JTextField();
    tfSurname = new javax.swing.JTextField();
    tbtnLock = new javax.swing.JToggleButton();
    dcValidFrom = new com.toedter.calendar.JDateChooser();
    dcValidTo = new com.toedter.calendar.JDateChooser();
    pRoles = new javax.swing.JPanel();
    pDTO = new javax.swing.JPanel();

    jList1.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jScrollPane1.setViewportView(jList1);

    org.openide.awt.Mnemonics.setLocalizedText(lUsername, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lUsername.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lFirstName, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lFirstName.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lSurname, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lSurname.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lValidFrom, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lValidFrom.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lValidTo, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lValidTo.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lRoles, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.lRoles.text")); // NOI18N

    btnChagngePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/lbenda/coursing/client/icon/user-password.png"))); // NOI18N
    org.openide.awt.Mnemonics.setLocalizedText(btnChagngePassword, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.btnChagngePassword.text")); // NOI18N
    btnChagngePassword.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnChagngePasswordActionPerformed(evt);
      }
    });

    tfUsername.setText(org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.tfUsername.text")); // NOI18N

    tfFirstname.setText(org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.tfFirstname.text")); // NOI18N

    tfSurname.setText(org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.tfSurname.text")); // NOI18N

    tbtnLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/lbenda/coursing/client/icon/unlocked.png"))); // NOI18N
    org.openide.awt.Mnemonics.setLocalizedText(tbtnLock, org.openide.util.NbBundle.getMessage(UserForm.class, "UserForm.tbtnLock.text")); // NOI18N
    tbtnLock.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/cz/lbenda/coursing/client/icon/locked.png"))); // NOI18N
    tbtnLock.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tbtnLockActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pRolesLayout = new javax.swing.GroupLayout(pRoles);
    pRoles.setLayout(pRolesLayout);
    pRolesLayout.setHorizontalGroup(
      pRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    pRolesLayout.setVerticalGroup(
      pRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );

    pDTO.setMaximumSize(new java.awt.Dimension(32767, 60));
    pDTO.setMinimumSize(new java.awt.Dimension(100, 60));
    pDTO.setPreferredSize(new java.awt.Dimension(0, 60));

    javax.swing.GroupLayout pDTOLayout = new javax.swing.GroupLayout(pDTO);
    pDTO.setLayout(pDTOLayout);
    pDTOLayout.setHorizontalGroup(
      pDTOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    pDTOLayout.setVerticalGroup(
      pDTOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 60, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(lFirstName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfFirstname))
      .addGroup(layout.createSequentialGroup()
        .addComponent(lUsername)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(tbtnLock, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnChagngePassword))
          .addComponent(tfUsername)))
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lSurname)
          .addComponent(lValidTo)
          .addComponent(lValidFrom)
          .addComponent(lRoles))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(dcValidFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(dcValidTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(tfSurname)
          .addComponent(pRoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
      .addComponent(pDTO, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(pDTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lUsername)
          .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnChagngePassword)
          .addComponent(tbtnLock))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(lFirstName)
              .addComponent(tfFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(lSurname)
              .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lValidFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(dcValidFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lValidTo)
          .addComponent(dcValidTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(lRoles)
            .addContainerGap(113, Short.MAX_VALUE))
          .addComponent(pRoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
  }// </editor-fold>//GEN-END:initComponents

  private JCheckBoxList cblRoles;
  private final Map<UserRole, JCheckBox> rolesCheckBoxes = new HashMap<>();

  private void initRolesList() {
    DefaultListModel<JCheckBox> model = new DefaultListModel<>();
    cblRoles = new JCheckBoxList(model);
    for (UserRole role : UserRole.values()) {
      JCheckBox chb = new JCheckBox(role.name());
      rolesCheckBoxes.put(role, chb);
      model.addElement(chb);
    }
    this.pRoles.setLayout(new BorderLayout());
    this.pRoles.add(cblRoles, BorderLayout.CENTER);
  }

  private void tbtnLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnLockActionPerformed

  }//GEN-LAST:event_tbtnLockActionPerformed

  private void btnChagngePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChagngePasswordActionPerformed
    ChangePasswordForm.showDialog(user);
  }//GEN-LAST:event_btnChagngePasswordActionPerformed

  private void doFireChange(String propName, Object origO, Object newO) {
    if (origO == null && newO == null) { return; }
    if (origO == null) { pcs.firePropertyChange(propName, origO, newO); return; }
    if (origO.getClass().isArray()) {
      if (Arrays.equals((Object[]) origO, (Object[]) newO)) { return; }
    } else  if (origO.equals(newO)) { return; }
    pcs.firePropertyChange(propName, origO, newO);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnChagngePassword;
  private com.toedter.calendar.JDateChooser dcValidFrom;
  private com.toedter.calendar.JDateChooser dcValidTo;
  private javax.swing.JList jList1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lFirstName;
  private javax.swing.JLabel lRoles;
  private javax.swing.JLabel lSurname;
  private javax.swing.JLabel lUsername;
  private javax.swing.JLabel lValidFrom;
  private javax.swing.JLabel lValidTo;
  private javax.swing.JPanel pDTO;
  private javax.swing.JPanel pRoles;
  private javax.swing.JToggleButton tbtnLock;
  private javax.swing.JTextField tfFirstname;
  private javax.swing.JTextField tfSurname;
  private javax.swing.JTextField tfUsername;
  // End of variables declaration//GEN-END:variables

  @Override
  public void setObject(Object bean) {
    this.user = (User) bean;

    this.tfFirstname.setText(user.getFirstName());
    this.tfSurname.setText(user.getLastName());
    this.tfUsername.setText(user.getUsername());
    this.tbtnLock.setSelected(user.isLocked());
    this.dcValidFrom.setDate(user.getValidFrom());
    this.dcValidTo.setDate(user.getValidTo());

    for (UserRole role : UserRole.values()) {
      this.rolesCheckBoxes.get(role).setSelected(false);
    }
    for (UserRole role : user.getRoles()) {
      this.rolesCheckBoxes.get(role).setSelected(true);
    }
  }

  public User getUser() {
    user.setFirstName(tfFirstname.getText());
    user.setLastName(tfSurname.getText());
    user.setUsername(tfUsername.getText());
    user.setValidFrom(dcValidFrom.getDate());
    user.setValidTo(dcValidTo.getDate());
    user.setLocked(tbtnLock.isSelected());
    List<UserRole> roles = new ArrayList<>();
    for (JCheckBox cb : this.cblRoles.getSelectedValuesList()) {
      if (cb.isSelected()) { roles.add(UserRole.valueOf(cb.getText())); }
    }
    user.setRoles(roles.toArray(new UserRole[roles.size()]));
    return user;
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener l) {
    pcs.addPropertyChangeListener(l);
  }
  @Override
  public void removePropertyChangeListener(PropertyChangeListener l) {
    pcs.removePropertyChangeListener(l);
  }

  public static void showDialog(User user) {
    final UserForm form = new UserForm();
    form.setObject(user);
    NotifyDescriptor nd = new NotifyDescriptor.Confirmation(form, Bundle.DLG_UserForm_Save(),
            NotifyDescriptor.OK_CANCEL_OPTION);
    if (NotifyDescriptor.OK_OPTION.equals(DialogDisplayer.getDefault().notify(nd))) {
      UserService us = ClientServiceLocator.getInstance().getBean(UserService.class);
      us.save(form.getUser());
    }
  }
}
