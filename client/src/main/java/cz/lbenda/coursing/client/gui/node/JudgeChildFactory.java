/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
