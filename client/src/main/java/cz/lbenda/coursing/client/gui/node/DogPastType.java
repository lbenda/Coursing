/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.client.gui.ChooseDialog;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.dto.DogLap;
import cz.lbenda.coursing.dto.DogPlacement;
import cz.lbenda.coursing.dto.Lap;
import cz.lbenda.coursing.dto.Race;
import cz.lbenda.coursing.service.DogLapService;
import cz.lbenda.coursing.service.DogPlacementService;
import cz.lbenda.coursing.service.LapService;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.util.NbBundle.Messages;
import org.openide.util.datatransfer.PasteType;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "TITLE_DogPastType_lap=Choose lap"
})
public class DogPastType extends PasteType {
  private Lap lap;
  private final Dog dog;
  private Race race;

  public DogPastType(Lap lap, Dog dog) { this(null, lap, dog); }
  public DogPastType(Lap lap, DogNode dogNode) { this(null, lap, dogNode.getDog()); }
  public DogPastType(Race race, Dog dog) { this(race, null, dog); }
  public DogPastType(Race race, DogNode dogNode) { this(race, null, dogNode.getDog()); }

  public DogPastType(Race race, Lap lap, Dog dog) {
    this.race = race;
    this.dog = dog;
    this.lap = lap;
  }

  @Override
  public Transferable paste() throws IOException {
    if (lap == null) {
      if (race.getLaps().isEmpty()) {
        LapService ls = ClientServiceLocator.getInstance().getBean(LapService.class);
        lap = ls.createNew();
        lap.setPostion(1);
        lap.setRace(race);
        ls.save(lap);
        race.getLaps().add(lap);
      } else if (race.getLaps().size() == 1) {
        lap = race.getLaps().get(0);
      } else {
        lap = ChooseDialog.showDialog(Bundle.TITLE_DogPastType_lap(),
                race.getLaps().toArray(new Lap[race.getLaps().size()]));
      }
    }
    if (lap == null) { return null; }
    if (race == null) { race = lap.getRace(); }
    DogLapService dls = ClientServiceLocator.getInstance().getBean(DogLapService.class);
    DogLap dogLap = dls.createNew();
    dogLap.setLap(lap);
    dogLap.setDog(dog);
    lap.getDogLaps().add(dogLap);

    DogPlacementService dps = ClientServiceLocator.getInstance().getBean(DogPlacementService.class);
    DogPlacement dp = dps.createNew();
    dp.setRace(race);
    dp.setDog(dog);

    dps.save(dp);
    dls.save(dogLap);
    return null;
  }
}
