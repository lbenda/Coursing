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
