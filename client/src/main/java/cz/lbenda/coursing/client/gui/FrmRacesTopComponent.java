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
import cz.lbenda.coursing.client.gui.node.DogNode;
import cz.lbenda.coursing.client.gui.node.RaceRootNode;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.DogService;
import java.awt.BorderLayout;
import java.beans.IntrospectionException;
import java.util.List;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//cz.lbenda.coursing.races//FrmRaces//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "FrmRacesTopComponent",
        iconBase="cz/lbenda/coursing/client/icon/race.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "cz.lbenda.coursing.races.FrmRacesTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FrmRacesAction",
        preferredID = "FrmRacesTopComponent"
)
@Messages({
    "CTL_FrmRacesAction=Races",
    "CTL_FrmRacesTopComponent=Races",
    "HINT_FrmRacesTopComponent=List of all races"
})
public final class FrmRacesTopComponent extends TopComponent implements ExplorerManager.Provider {

  private static final Logger LOG = LoggerFactory.getLogger(FrmRacesTopComponent.class);

  private final ExplorerManager em = new ExplorerManager();
  private final BeanTreeView beanTreeView;

  public FrmRacesTopComponent() {
    initComponents();
    setName(Bundle.CTL_FrmRacesTopComponent());
    setToolTipText(Bundle.HINT_FrmRacesTopComponent());

    beanTreeView = new BeanTreeView();
    beanTreeView.setRootVisible(true);
    setLayout(new BorderLayout());
    add(BorderLayout.CENTER, beanTreeView);
    em.setRootContext(new RaceRootNode());

    associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
  }

  @Override
  public ExplorerManager getExplorerManager() {
    return em;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 639, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 328, Short.MAX_VALUE)
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


  private static class DogChildFactory extends ChildFactory<Dog> {
    private final DogService dogService = ClientServiceLocator.getInstance().getBean(DogService.class);
    private final Race race;
    public DogChildFactory(Race race) {
      this.race = race;
    }
    @Override
    protected boolean createKeys(List<Dog> toPopulate) { // FIXME
      //race.getLaps()
      toPopulate.addAll(dogService.allEntities());
      return true;
    }
    @Override
    protected Node createNodeForKey(Dog key) {
      try {
        return new DogNode(key);
      } catch (IntrospectionException ex) {
        LOG.error("Faild the creating node of DTO", ex);
      }
      return null;
    }
  }
}
