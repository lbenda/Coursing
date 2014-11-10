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
