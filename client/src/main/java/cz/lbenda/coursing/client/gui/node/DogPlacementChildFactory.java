/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.DogPlacement;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.DogPlacementService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogPlacementChildFactory extends AbstractChildFactory<DogPlacement> implements AbstractDTOService.DTOChangedListener<DogPlacement> {

  private static final Logger LOG = LoggerFactory.getLogger(DogPlacementChildFactory.class);

  private final Race race;

  public DogPlacementChildFactory(Race race) {
    super();
    this.race = race;
    ClientServiceLocator.getInstance().getBean(DogPlacementService.class).addDTOChangedListener(this);
  }

  @Override
  protected boolean generateKeys(List<DogPlacement> toPopulate) {
    toPopulate.clear();
    toPopulate.addAll(race.getDogPlacements());
    return true;
  }

  @Override
  protected Node createNode(DogPlacement key) {
    try {
      return new DogPlacementNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Faild the creating node of DTO", ex);
    }
    return null;
  }

  @Override
  public void dtoChanged(DogPlacement oldDTO, DogPlacement newDTO) {
    if ((oldDTO != null && race.equals(oldDTO.getRace()))
            || (newDTO != null && race.equals(newDTO.getRace()))) {
      this.refresh(true);
    }
  }
}
