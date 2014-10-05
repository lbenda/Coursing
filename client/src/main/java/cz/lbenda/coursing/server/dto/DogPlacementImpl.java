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
import cz.lbenda.coursing.dto.DogPlacement;
import cz.lbenda.coursing.dto.Race;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/22/14.
 */
@Entity(name = "DogPlacement")
public class DogPlacementImpl extends DTOImpl implements DogPlacement {

  @ManyToOne(targetEntity = RaceImpl.class)
  @JoinColumn(name = "Race_id")
  private Race race;
  @ManyToOne(targetEntity = DogImpl.class)
  @JoinColumn(name = "Dog_id")
  private Dog dog;
  @Column
  private Integer placementGenderBreed;
  @Column
  private Integer placementBreed;
  @Column
  private Integer placementGender;
  @Column
  private Integer placementTotal;

  public Race getRace() { return race; }
  public void setRace(Race race) { this.race = race; }

  public Dog getDog() { return dog; }
  public void setDog(Dog dog) { this.dog = dog; }

  public Integer getPlacementGenderBreed() { return placementGenderBreed; }
  public void setPlacementGenderBreed(Integer placementGenderBreed) { this.placementGenderBreed = placementGenderBreed; }

  public Integer getPlacementBreed() { return placementBreed; }
  public void setPlacementBreed(Integer placementBreed) { this.placementBreed = placementBreed; }

  public Integer getPlacementGender() { return placementGender; }
  public void setPlacementGender(Integer placementGender) { this.placementGender = placementGender; }

  public Integer getPlacementTotal() { return placementTotal; }
  public void setPlacementTotal(Integer placementTotal) { this.placementTotal = placementTotal; }
}
