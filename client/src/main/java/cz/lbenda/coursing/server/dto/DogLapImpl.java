/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>.
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
package cz.lbenda.coursing.server.dto;

import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.dto.DogLap;
import cz.lbenda.coursing.dto.Lap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Entity(name = "DogLap")
public class DogLapImpl extends DTOImpl implements DogLap {

  @ManyToOne(targetEntity = LapImpl.class)
  @JoinColumn(name="Lap_id")
  private Lap lap;
  @ManyToOne(targetEntity = DogImpl.class)
  @JoinColumn(name="Dog_id")
  private Dog dog;
  @Column
  private Integer speed;
  @Column
  private Integer enthusiasm;
  @Column
  private Integer agility;
  @Column
  private Integer endurance;

  public Lap getLap() { return lap; }
  public void setLap() { this.lap = lap; }

  public Dog getDog() { return dog; }
  public void setDog() { this.dog = dog; }

  public Integer getSpeed() { return speed; }
  public void setSpeed(Integer Speed) { this.speed = speed; }

  public Integer getEnthusiasm() { return enthusiasm; }
  public void setEnthusiasm(Integer enthusiasm) { this.enthusiasm = enthusiasm; }

  public Integer getAgility() { return agility; }
  public void setAgility(Integer agility) { this.agility = agility; }

  public Integer getEndurance() { return endurance; }
  public void setEndurance(Integer endurance) { this.endurance = endurance; }
}
