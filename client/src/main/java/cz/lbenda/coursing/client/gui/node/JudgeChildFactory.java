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
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.AbstractDTOService;
import cz.lbenda.coursing.service.JudgeService;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class JudgeChildFactory extends AbstractChildFactory<Judge> implements AbstractDTOService.DTOChangedListener<Judge> {

  private static Logger LOG = LoggerFactory.getLogger(JudgeChildFactory.class);

  private final JudgeService judgeService = ClientServiceLocator.getInstance().getBean(JudgeService.class);
  private final Race race;

  public JudgeChildFactory() {
    this(null);
  }

  public JudgeChildFactory(Race race) {
    super();
    judgeService.addDTOChangedListener(this);
    this.race = race;
  }

  @Override
  protected boolean generateKeys(List<Judge> toPopulate) {
    if (race == null) { toPopulate.addAll(judgeService.allEntities()); }
    else { toPopulate.addAll(race.getJudges()); }
    return true;
  }

  @Override
  protected Node createNode(Judge key) {
    try {
      return new JudgeNode(key);
    } catch (IntrospectionException ex) {
      LOG.error("Faild the creating node of DTO", ex);
    }
    return null;
  }

  @Override
  public void dtoChanged(Judge oldDTO, Judge newDTO) {
    if (race == null || (oldDTO != null && race.getJudges().contains(oldDTO))
            || (newDTO != null && race.getJudges().contains(newDTO))) {
      this.refresh(true);
    }
  }
}
