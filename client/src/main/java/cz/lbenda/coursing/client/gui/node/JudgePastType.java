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
import cz.lbenda.coursing.service.RaceService;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.util.datatransfer.PasteType;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class JudgePastType extends PasteType {
  private final Race race;
  private final Judge judge;
  public JudgePastType(Race race, JudgeNode jn) {
    this.race = race;
    this.judge = jn.getJudge();
  }
  public JudgePastType(Race race, Judge judge) {
    this.race = race;
    this.judge = judge;
  }
  @Override
  public Transferable paste() throws IOException {
    race.getJudges().add(judge);
    RaceService rs = ClientServiceLocator.getInstance().getBean(RaceService.class);
    rs.save(race);
    return null;
  }
}
