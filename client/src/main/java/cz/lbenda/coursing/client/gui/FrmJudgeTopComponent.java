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

import cz.lbenda.coursing.client.gui.node.JudgeRootNode;
import java.awt.BorderLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//cz.lbenda.coursing.client.gui//FrmJudge//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "FrmJudgeTopComponent",
        iconBase = "cz/lbenda/coursing/client/icon/judge.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "cz.lbenda.coursing.client.gui.FrmJudgeTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FrmJudgeAction",
        preferredID = "FrmJudgeTopComponent"
)
@Messages({
  "CTL_FrmJudgeAction=Judges",
  "CTL_FrmJudgeTopComponent=Judges",
  "HINT_FrmJudgeTopComponent=All Judges in system"
})
public final class FrmJudgeTopComponent extends TopComponent implements ExplorerManager.Provider {

  private static final Logger LOG = LoggerFactory.getLogger(FrmJudgeTopComponent.class);

  private final ExplorerManager em = new ExplorerManager();
  private final BeanTreeView beanTreeView;

  public FrmJudgeTopComponent() {
    initComponents();
    setName(Bundle.CTL_FrmJudgeTopComponent());
    setToolTipText(Bundle.HINT_FrmJudgeTopComponent());

    beanTreeView = new BeanTreeView();
    beanTreeView.setRootVisible(true);
    setLayout(new BorderLayout());
    add(BorderLayout.CENTER, beanTreeView);
    em.setRootContext(new JudgeRootNode());

    associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
  }

  @Override
  public ExplorerManager getExplorerManager() {
    return em;
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
  @Override
  public void componentOpened() {
    // TODO add custom code on component opening
  }

  @Override
  public void componentClosed() {
    // TODO add custom code on component closing
  }

  void writeProperties(java.util.Properties p) {
    // better to version settings since initial version as advocated at
    // http://wiki.apidesign.org/wiki/PropertyFiles
    p.setProperty("version", "1.0");
    // TODO store your settings
  }

  void readProperties(java.util.Properties p) {
    String version = p.getProperty("version");
    // TODO read your settings according to their version
  }

}
