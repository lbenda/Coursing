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
