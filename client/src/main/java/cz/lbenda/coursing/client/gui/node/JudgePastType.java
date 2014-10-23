/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
