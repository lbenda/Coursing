/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.RaceService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class RaceChildFactory extends AbstractChildFactory<Race> implements AbstractDTOService.DTOChangedListener<Race> {

  private static final Logger LOG = LoggerFactory.getLogger(RaceChildFactory.class);

  private final RaceService raceService = ClientServiceLocator.getInstance().getBean(RaceService.class);

  public RaceChildFactory() {
    super();
    raceService.addDTOChangedListener(this);
  }

  @Override
  protected boolean generateKeys(List<Race> toPopulate) {
    toPopulate.addAll(raceService.allEntities());
    return true;
  }

  @Override
  protected Node createNode(Race key) {
    try {
      return new RaceNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Faild the creating node of DTO", ex);
    }
    return null;
  }

  @Override
  public void dtoChanged(Race oldDTO, Race newDTO) {
    this.refresh(true);
  }
}
