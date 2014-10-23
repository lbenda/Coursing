/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui;

import cz.lbenda.coursing.client.gui.node.UserRootNode;
import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.service.SecurityService;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import cz.lbenda.coursing.user.UserService;
import java.awt.BorderLayout;
import java.lang.ref.WeakReference;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//cz.lbenda.coursing.client.gui//FrmUsers//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "FrmUsersTopComponent",
        iconBase = "cz/lbenda/coursing/client/icon/users.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "cz.lbenda.coursing.client.gui.FrmUsersTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FrmUsersAction",
        preferredID = "FrmUsersTopComponent"
)
@Messages({
  "CTL_FrmUsersAction=Users",
  "CTL_FrmUsersTopComponent=Users",
  "HINT_FrmUsersTopComponent=Show and edit users in application"
})
public final class FrmUsersTopComponent extends TopComponent implements ExplorerManager.Provider{

  private static final Logger LOG = LoggerFactory.getLogger(FrmUsersTopComponent.class);
  private final ExplorerManager em = new ExplorerManager();
  private final BeanTreeView tree;
  private final UserService userService;
  private final SecurityService securityService;

  private final InstanceContent ic = new InstanceContent();

  public FrmUsersTopComponent() {
    initComponents();
    setName(Bundle.CTL_FrmUsersTopComponent());
    setToolTipText(Bundle.HINT_FrmUsersTopComponent());
    setLayout(new BorderLayout());

    userService = ClientServiceLocator.getInstance().getBean(UserService.class);
    securityService = ClientServiceLocator.getInstance().getBean(SecurityService.class);

    tree = new BeanTreeView();
    tree.setRootVisible(true);
    add(tree, BorderLayout.CENTER);
    associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
  }

  public User currentUser() {
    return securityService.getCurrentUser();
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

  private WeakReference<UserRootNode> urn;

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
  @Override
  public void componentOpened() {
    if (currentUser() != null && UserRole.ADMIN.inArray(currentUser().getRoles())) {
      // ic.add(userAddCookie);
    } else {
      LOG.debug("Current user: " + currentUser());
    }

    UserRootNode urn = this.urn == null ? null : this.urn.get();
    if (urn == null) {
      urn = new UserRootNode();
      this.urn = new WeakReference(urn);
    } else { urn.open(); }
    em.setRootContext(urn);
  }

  @Override
  public void componentClosed() {
    final UserRootNode urn = this.urn == null ? null : this.urn.get();
    if (urn != null) { urn.close(); }
    em.setRootContext(new AbstractNode(Children.LEAF));
    // ic.remove(userAddCookie);
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
