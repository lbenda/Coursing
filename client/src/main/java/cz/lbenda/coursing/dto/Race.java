/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.dto;

import java.util.Date;
import java.util.List;

/**
 * DTO object which hold information about database entity Race.
 * @author benzin
 */
public interface Race extends DTO {
  String getName();
  void setName(String name);

  Date getDateOfRace();
  void setDateOfRace(Date dateOfRace);

  String getPlace();
  void setPlace(String place);

  String getComment();
  void setComment(String comment);

  RaceType getRaceType();
  void setRaceType(RaceType raceType);

  List<Judge> getJudges();

  List<Lap> getLaps();

  List<DogPlacement> getDogPlacements();
}
