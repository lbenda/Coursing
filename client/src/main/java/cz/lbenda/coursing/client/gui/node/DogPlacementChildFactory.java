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
