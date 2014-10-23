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
import cz.lbenda.coursing.dto.Race;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Entity(name = "Lap")
public class LapImpl extends DTOImpl implements Lap {

  @Column
  private int position;
  @Column
  private Integer lapLength;
  @ManyToOne(targetEntity = RaceImpl.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "Race_id")
  private Race race;
  @OneToMany(targetEntity = DogLapImpl.class, mappedBy="lap")
  private List<DogLap> dogLaps;

  public @Override int getPosition() { return position; }
  public @Override void setPostion(int position) { this.position = position; }

  public @Override Integer getLapLength() { return lapLength; }
  public @Override void setLapLength(Integer lapLength) { this.lapLength = lapLength; }

  public @Override Race getRace() { return race; }
  public @Override void setRace(Race race) { this.race = race; }

  public @Override Map<Dog, DogLap> getDog2Laps() {
    Map<Dog, DogLap> result = new HashMap<>();
    for (DogLap dLap : getDogLaps()) { result.put(dLap.getDog(), dLap); }
    return result;
  }

  public @Override List<DogLap> getDogLaps() {
    if (dogLaps == null) { dogLaps = new ArrayList<>(4); }
    return dogLaps;
  }
}
