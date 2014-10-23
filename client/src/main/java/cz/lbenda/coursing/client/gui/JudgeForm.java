/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.service.JudgeService;
import java.awt.BorderLayout;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle.Messages;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "DLG_JudgeForm_Save=Save changes in judge"
})
public class JudgeForm extends javax.swing.JPanel {

  private DTOPanel dtoPanel;

  /**
   * Creates new form JudgeForm
   */
  public JudgeForm() {
    initComponents();
    initComponentsDTO();
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

    pDTO = new javax.swing.JPanel();
    lFirstname = new javax.swing.JLabel();
    lLastname = new javax.swing.JLabel();
    lComent = new javax.swing.JLabel();
    tfFirstname = new javax.swing.JTextField();
    tfLastname = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    taComment = new javax.swing.JTextArea();

    pDTO.setMaximumSize(new java.awt.Dimension(32767, 60));
    pDTO.setMinimumSize(new java.awt.Dimension(100, 60));
    pDTO.setPreferredSize(new java.awt.Dimension(0, 60));

    javax.swing.GroupLayout pDTOLayout = new javax.swing.GroupLayout(pDTO);
    pDTO.setLayout(pDTOLayout);
    pDTOLayout.setHorizontalGroup(
      pDTOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    pDTOLayout.setVerticalGroup(
      pDTOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 60, Short.MAX_VALUE)
    );

    org.openide.awt.Mnemonics.setLocalizedText(lFirstname, org.openide.util.NbBundle.getMessage(JudgeForm.class, "JudgeForm.lFirstname.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lLastname, org.openide.util.NbBundle.getMessage(JudgeForm.class, "JudgeForm.lLastname.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(lComent, org.openide.util.NbBundle.getMessage(JudgeForm.class, "JudgeForm.lComent.text")); // NOI18N

    tfFirstname.setText(org.openide.util.NbBundle.getMessage(JudgeForm.class, "JudgeForm.tfFirstname.text")); // NOI18N

    tfLastname.setText(org.openide.util.NbBundle.getMessage(JudgeForm.class, "JudgeForm.tfLastname.text")); // NOI18N

    taComment.setColumns(20);
    taComment.setRows(5);
    jScrollPane1.setViewportView(taComment);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pDTO, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(layout.createSequentialGroup()
            .addComponent(lComent)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(lLastname)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tfLastname))
          .addGroup(layout.createSequentialGroup()
            .addComponent(lFirstname)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tfFirstname)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(pDTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lFirstname)
          .addComponent(tfFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lLastname)
          .addComponent(tfLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(lComent)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lComent;
  private javax.swing.JLabel lFirstname;
  private javax.swing.JLabel lLastname;
  private javax.swing.JPanel pDTO;
  private javax.swing.JTextArea taComment;
  private javax.swing.JTextField tfFirstname;
  private javax.swing.JTextField tfLastname;
  // End of variables declaration//GEN-END:variables

  private Judge judge;

  public void setJudge(Judge judge) {
    this.judge = judge;
    dtoPanel.setDTO(judge);
    tfFirstname.setText(judge.getFirstName());
    tfLastname.setText(judge.getLastName());
    taComment.setText(judge.getComment());
  }

  public Judge getJudge() {
    judge.setFirstName(tfFirstname.getText());
    judge.setLastName(tfLastname.getText());
    judge.setComment(taComment.getText());
    return judge;
  }

  public static void showDialog(Judge judge) {
    final JudgeForm form = new JudgeForm();
    form.setJudge(judge);
    NotifyDescriptor nd = new NotifyDescriptor.Confirmation(form, Bundle.DLG_JudgeForm_Save(),
            NotifyDescriptor.OK_CANCEL_OPTION);
    if (NotifyDescriptor.OK_OPTION.equals(DialogDisplayer.getDefault().notify(nd))) {
      JudgeService judgeService = ClientServiceLocator.getInstance().getBean(JudgeService.class);
      judgeService.save(form.getJudge());
    }
  }
}
