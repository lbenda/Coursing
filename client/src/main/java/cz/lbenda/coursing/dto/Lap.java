/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.lbenda.coursing.dto;

import java.util.List;
import java.util.Map;

/**
 * @author benzin
 */
public interface Lap extends DTO {

  Integer getLength();
  void setLength(Integer length);

  Race getRace();
  void setRace(Race race);

  Map<Dog, DogLap> getDog2Laps();

  List<DogLap> getDogLaps();
}
