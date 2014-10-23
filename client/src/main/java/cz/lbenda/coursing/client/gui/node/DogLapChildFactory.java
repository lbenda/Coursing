/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.DogLap;
import cz.lbenda.coursing.dto.Lap;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.DogLapService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogLapChildFactory extends AbstractChildFactory<DogLap> implements AbstractDTOService.DTOChangedListener<DogLap> {

  private static final Logger LOG = LoggerFactory.getLogger(DogLapChildFactory.class);

  private final Lap lap;

  public DogLapChildFactory(Lap lap) {
    ClientServiceLocator.getInstance().getBean(DogLapService.class).addDTOChangedListener(this);
    this.lap = lap;
  }

  @Override
  protected boolean generateKeys(List<DogLap> toPopulate) {
    toPopulate.clear();
    toPopulate.addAll(lap.getDogLaps()); //}
    return true;
  }

  @Override
  protected Node createNode(DogLap key) {
    try {
      return new DogLapNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Faild the creating node of DTO", ex);
    }
    return null;
  }

  @Override
  public void dtoChanged(DogLap oldDTO, DogLap newDTO) {
    if ((oldDTO != null && lap.equals(oldDTO.getLap()))
            || (newDTO != null && lap.equals(newDTO.getLap()))) {
      this.refresh(true);
    }
  }
}
