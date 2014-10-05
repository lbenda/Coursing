/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.lbenda.coursing.dto;

/**
 * @author benzin
 */
public interface DogPlacement extends DTO {
  Race getRace();
  void setRace(Race race);

  Dog getDog();
  void setDog(Dog dog);

  Integer getPlacementGenderBreed();
  void setPlacementGenderBreed(Integer placementGenderBreed);

  Integer getPlacementBreed();
  void setPlacementBreed(Integer placementBreed);

  Integer getPlacementGender();
  void setPlacementGender(Integer placementGender);

  Integer getPlacementTotal();
  void setPlacementTotal(Integer placementTotal);
}
