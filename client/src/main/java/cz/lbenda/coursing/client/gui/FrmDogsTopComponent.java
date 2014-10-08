/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.action.DogAddCookie;
import cz.lbenda.coursing.client.gui.beans.DogImplBeanInfo;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.service.AbstractDTOService.DTOChangedListener;
import cz.lbenda.coursing.service.DogService;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import javax.swing.JTable;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.swing.outline.DefaultOutlineCellRenderer;
import org.openide.actions.PropertiesAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Top components with table of dogs
 */
@ConvertAsProperties(
        dtd = "-//cz.lbenda.coursing.client.gui//FrmDogs//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "FrmDogsTopComponent",
        iconBase = "cz/lbenda/coursing/client/icon/dog.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "cz.lbenda.coursing.client.gui.FrmDogsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FrmDogsAction",
        preferredID = "FrmDogsTopComponent"
)
@Messages({
  "CTL_FrmDogsAction=Dogs table",
  "CTL_FrmDogsTopComponent=Dogs table",
  "HINT_FrmDogsTopComponent=Show all dogs in database"
})
public final class FrmDogsTopComponent extends TopComponent implements ExplorerManager.Provider {

  private static final Logger LOG = LoggerFactory.getLogger(FrmDogsTopComponent.class);
  private final ExplorerManager em = new ExplorerManager();
  private final OutlineView ov;
  private final InstanceContent ic = new InstanceContent();
  private DogChildFactory dogChildrenFactory;
  private DTOChangedListener<Dog> DTOChangedListener;

  public FrmDogsTopComponent() {
    initComponents();
    setName(Bundle.CTL_FrmDogsTopComponent());
    setToolTipText(Bundle.HINT_FrmDogsTopComponent());

    setLayout(new BorderLayout());
    ov = new OutlineView();
    ov.getOutline().setRootVisible(false);
    ov.getOutline().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    ColumnCellRenderer ccr = new ColumnCellRenderer();

    ccr.setCentered(false);
    // ov.getOutline().setDefaultRenderer(Node.Property.class, ccr);
    ov.getOutline().setDefaultRenderer(Node.Property.class, new DefaultOutlineCellRenderer());
    add(ov, BorderLayout.CENTER);
    dogChildrenFactory = new DogChildFactory();
    Node rootNode = new AbstractNode(Children.create(dogChildrenFactory, true));
    em.setRootContext(rootNode);
    definePropertyAndHint();

    ic.add(dogAddCookie);
    ic.add(em);
    ic.add(getActionMap());
    associateLookup(new AbstractLookup(ic));
  }

  private void definePropertyAndHint() {
    String[] nameDispName = new String[DogImplBeanInfo.SHOWN_PROPERTIES.length * 2];
    for (int i = 0; i < DogImplBeanInfo.SHOWN_PROPERTIES.length; i++) {
      nameDispName[i * 2] = DogImplBeanInfo.SHOWN_PROPERTIES[i];
      nameDispName[i * 2 + 1] = DogImplBeanInfo.propertyDisplayName(DogImplBeanInfo.SHOWN_PROPERTIES[i]);
    }
    LOG.debug(Arrays.toString(nameDispName));
    ov.setPropertyColumns(nameDispName);
    for (String prop : DogImplBeanInfo.SHOWN_PROPERTIES) {
      LOG.debug("Property '" + prop + "' description " + DogImplBeanInfo.propertyShortDescription(prop));
      ov.setPropertyColumnDescription(prop, DogImplBeanInfo.propertyShortDescription(prop));
    }
  }

    @Override
  public ExplorerManager getExplorerManager() {
    return em;
  }

  private DogAddCookie dogAddCookie = new DogAddCookie() {
    @Override
    public void addNewDog(ActionEvent ev) {
      DogService dogService = ClientServiceLocator.getInstance().getBean(DogService.class);
      try {
        Dog newDog = dogService.createNew();
        FrmDogsTopComponent.DogNode node = new FrmDogsTopComponent.DogNode(newDog);
        ev.setSource(node);
        node.getPreferredAction().actionPerformed(ev);
      } catch (IntrospectionException ex) {
        LOG.error("Faild when dog node is created", ex);
        throw new RuntimeException("Faild when dog node is created", ex);
      }
    }
  };

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
    dogChildrenFactory.open();
  }

  @Override
  public void componentClosed() {
    dogChildrenFactory.close();
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

  public static class DogNode extends BeanNode<Dog> {
    public DogNode(final Dog dog) throws IntrospectionException {
      this(dog, new InstanceContent());
    }
    public DogNode(final Dog dog, final InstanceContent ic) throws IntrospectionException {
      super(dog, Children.LEAF, new AbstractLookup(ic));
    }
    @Override
    public Action getPreferredAction() {
      return SystemAction.get(PropertiesAction.class);
    }
    @Override
    public Action[] getActions(boolean context) {
      return new Action[]{SystemAction.get(PropertiesAction.class)};
    }
  }

  public class DogChildFactory extends ChildFactory<Dog> implements DTOChangedListener<Dog> {
    private final DogService dogService = ClientServiceLocator.getInstance().getBean(DogService.class);
    private final List<Dog> newDogs = new ArrayList<>();
    public DogChildFactory() {
    }
    public final void close() {
      dogService.removeDTOChangedListener(this);
      newDogs.clear();
    }
    public final void open() {
      dogService.addDTOChangedListener(this);
    }
    @Override
    protected  boolean createKeys(List<Dog> toPopulate) {
      LOG.debug("createKeys");
      if (toPopulate.isEmpty()) { toPopulate.addAll(dogService.allEntities()); } // Adding completely new data
      for (Dog dog : newDogs) {
        if (!toPopulate.contains(dog)) { toPopulate.add(dog); }
      }
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
    @Override
    public void dtoChanged(Dog oldDTO, Dog newDTO) {
      LOG.debug("oldDTO, newDTO");
      if (oldDTO == null && !newDogs.contains(newDTO)) {
        LOG.debug("refresh");
        newDogs.add(newDTO);
        refresh(true);
      }
    }
  }
}
