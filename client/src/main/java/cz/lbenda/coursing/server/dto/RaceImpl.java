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

import cz.lbenda.coursing.dto.DogPlacement;
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.dto.Lap;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.dto.RaceType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Entity(name = "Race")
public class RaceImpl extends DTOImpl implements Race {

  @Column(name = "raceName", length = 100, nullable = false)
  private String name;
  @Column
  @Temporal(TemporalType.DATE)
  private Date dateOfRace;
  @Column(length = 250, nullable = false)
  private String place;
  @Column(length = 1000)
  private String comment;
  @ManyToOne(targetEntity = RaceTypeImpl.class)
  @JoinColumn(name = "RaceType_id")
  private RaceType raceType;
  @ManyToMany(targetEntity =  JudgeImpl.class)
  @JoinTable(name = "J_Race_Judge",
      joinColumns={@JoinColumn(name = "Race_id", referencedColumnName = "id")},
      inverseJoinColumns={@JoinColumn(name = "Judge_id", referencedColumnName = "id")})
  private List<Judge> judges;
  @OneToMany(targetEntity = LapImpl.class, mappedBy="race")
  private List<Lap> laps;
  @OneToMany(targetEntity = DogPlacementImpl.class, mappedBy="race")
  private List<DogPlacement> dogPlacements;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Date getDateOfRace() { return dateOfRace; }
  public void setDateOfRace(Date dateOfRace) { this.dateOfRace = dateOfRace; }

  public String getPlace() { return place; }
  public void setPlace(String place) { this.place = place; }

  public String getComment() { return comment; }
  public void setComment(String comment) { this.comment = comment; }

  public RaceType getRaceType() { return raceType; }
  public void setRaceType(RaceType raceType) { this.raceType = raceType; }

  public List<Judge> getJudges() {
    if (judges == null) { judges = new ArrayList<Judge>(); }
    return judges;
  }

  public List<Lap> getLaps() {
    if (laps == null) { laps = new ArrayList<Lap>(); }
    return laps;
  }

  public List<DogPlacement> getDogPlacements() {
    if (dogPlacements == null) { dogPlacements = new ArrayList<DogPlacement>(); }
    return dogPlacements;
  }
}
