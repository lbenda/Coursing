/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.FrmDogsTopComponent;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.DogService;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogChildFactory extends AbstractChildFactory<Dog> implements AbstractDTOService.DTOChangedListener<Dog> {

  private static final Logger LOG = LoggerFactory.getLogger(DogChildFactory.class);

  private final DogService dogService = ClientServiceLocator.getInstance().getBean(DogService.class);
  private final List<Dog> newDogs = new ArrayList<>();
  private final FrmDogsTopComponent outer;

  public DogChildFactory(final FrmDogsTopComponent outer) {
    super();
    this.outer = outer;
  }

  public final void close() {
    dogService.removeDTOChangedListener(this);
    newDogs.clear();
  }

  public final void open() {
    dogService.addDTOChangedListener(this);
  }

  @Override
  protected boolean generateKeys(List<Dog> toPopulate) {
    LOG.debug("createKeys");
    if (toPopulate.isEmpty()) {
      toPopulate.addAll(dogService.allEntities());
    } // Adding completely new data
    for (Dog dog : newDogs) {
      if (!toPopulate.contains(dog)) {
        toPopulate.add(dog);
      }
    }
    return true;
  }

  @Override
  protected Node createNode(Dog key) {
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
