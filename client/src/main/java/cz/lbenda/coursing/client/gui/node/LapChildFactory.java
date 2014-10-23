/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Lap;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.LapService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class LapChildFactory extends AbstractChildFactory<Lap> implements AbstractDTOService.DTOChangedListener<Lap> {

  private static final Logger LOG = LoggerFactory.getLogger(LapChildFactory.class);

  private final Race race;

  public LapChildFactory(Race race) {
    super();
    ClientServiceLocator.getInstance().getBean(LapService.class).addDTOChangedListener(this);
    this.race = race;
  }

  @Override
  protected boolean generateKeys(List<Lap> toPopulate) {
    toPopulate.addAll(race.getLaps());
    return true;
  }

  @Override
  protected Node createNode(Lap key) {
    try {
      return new LapNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Faild the creating node of DTO", ex);
    }
    return null;
  }

  @Override
  public void dtoChanged(Lap oldDTO, Lap newDTO) {
    if ((oldDTO != null && race.equals(oldDTO.getRace()))
            || (newDTO != null && race.equals(newDTO.getRace()))) {
      this.refresh(true);
    }
  }
}
