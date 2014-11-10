/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
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
package cz.lbenda.coursing.server;

import cz.lbenda.coursing.common.GenderType;
import cz.lbenda.coursing.dto.Breed;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.dto.Judge;
import cz.lbenda.coursing.dto.RaceType;
import cz.lbenda.coursing.service.AbstractPasswordGenerator;
import cz.lbenda.coursing.service.BreedService;
import cz.lbenda.coursing.service.DogService;
import cz.lbenda.coursing.service.JudgeService;
import cz.lbenda.coursing.service.RaceTypeService;
import cz.lbenda.coursing.user.User;
import cz.lbenda.coursing.user.UserRole;
import cz.lbenda.coursing.user.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/** Prepare DB
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class PrepareDB {

  private static final Logger LOG = LoggerFactory.getLogger(PrepareDB.class);

  @Autowired
  private BreedService breedService;
  @Autowired
  private UserService userService;
  @Autowired
  private RaceTypeService raceTypeService;
  @Autowired
  private JudgeService judgeService;
  @Autowired
  private DogService dogService;

  public void prepareDB() {
    this.createUsers();
    this.createJudges();
    this.createRaceType();
    this.createBreeds();
    this.createDogs();
  }

  public void createBreeds() {
    createNewBreed("Greyhound", null, 0);
    createNewBreed("Afghan", null, 1);
    createNewBreed("Azawagh", null, 2);
    createNewBreed("Barzoi", null, 3);
    createNewBreed("Chrt Polski", null, 4);
    createNewBreed("Galgo Espanol", null, 5);
    createNewBreed("Hortoya Borzaya", null, 6);
    createNewBreed("Irish Wolfhound", null, 7);
    createNewBreed("Italian Greyhound", null, 8);
    createNewBreed("Magyar Agar", null, 9);
    createNewBreed("Saluki", null, 10);
    createNewBreed("Scottish Deerhound", null, 11);
    createNewBreed("Silken Windhound", null, 12);
    createNewBreed("Sloughi", null, 13);
    createNewBreed("Whippet", null, 14);
    createNewBreed("Basenji", null, 15);
    createNewBreed("Rhodesian Ridgeback", null, 16);
  }
  private void createNewBreed(String name, String comment, int sort) {
    Breed breed = breedService.createNew();
    breed.setName(name);
    breed.setComment(comment);
    breed.setSort(sort);
    breedService.save(breed);
  }

  public void createRaceType() {
    createNewRaceType("Points race", "Budoucí závod", 0);
    createNewRaceType("CACT", null, 1);
    createNewRaceType("CACIL", null, 2);
    createNewRaceType("Normal race", "Normální závod", 3);
  }
  private void createNewRaceType(String name, String comment, int sort) {
    RaceType raceType = raceTypeService.createNew();
    raceType.setName(name);
    raceType.setComment(comment);
    raceType.setSort(sort);
    raceTypeService.save(raceType);
  }

  public void createUsers() {
    createNewUser(null, null, "admin", "admin", UserRole.ADMIN);
    createNewUser("peksha", "peksha", "peksha", "epIp99xx", UserRole.USER);
    createNewUser("Eva", "Dufkova", "dufca", "acfud", UserRole.USER);
  }
  private void createNewUser(String firstname, String lastname, String username, String password, UserRole role) {
    User user = userService.createNew();
    user.setFirstName(firstname);
    user.setLastName(lastname);
    user.setUsername(username);
    user.setPasswd(AbstractPasswordGenerator.generatePassword(password));
    user.setLocked(false);
    user.setValidFrom(new Date());
    user.setValidTo(null);
    user.setRoles(new UserRole[] { role });
    userService.save(user);
  }

  public void createJudges() {
    createNewJudge("Věra", "Malátková", null);
    createNewJudge("Petra", "Dittrichová", null);
  }
  private void createNewJudge(String firstname, String lastname, String comment) {
    Judge judge = judgeService.createNew();
    judge.setFirstName(firstname);
    judge.setLastName(lastname);
    judge.setComment(comment);
    judgeService.save(judge);
  }

  public void createDogs() {
    createNewDog("Ikaros Filemino", "Greyhound", GenderType.MAN, "ČMKU G 114/10", "2008-10-28", "Toth Petr");
    createNewDog("Anna Karenina Nijinski Ballet", "Borzoi", GenderType.WOMAN, "ČMKU B 946/08", "2006-06-03", "Horáková Johana");
    createNewDog("Felicity Esmé Silent Enigma", "Borzoi", GenderType.WOMAN, "ČMKU B 61/09", "2007-09-06", "Erika Reiszová");
    createNewDog("Imposant Imp Riuna", "Greyhound", GenderType.MAN, "ČMKU G 960/08", "2006-12-13", "Eva a Radovana Dufková");
    createNewDog("Ice Wind Filemino", "Greyhound", GenderType.MAN, "", "2008-10-28", "Panchartková V.");
    createNewDog("Vuai ak Ilaman", "Azawagh", GenderType.MAN, "ČMKU AZ 131/10", "2008-06-17", "Václava Lukešová");
    createNewDog("Baltazár Yellow treatures", "Whippet", GenderType.MAN, "SKJ 0234", "2006-06-19", "Zuzana Biskupská");
    createNewDog("Chivas Paluduz", "Irish Wolfhound", GenderType.MAN, "ČMKU IVV 47/08", "2006-03-03", "Iveta Prokešová");
    createNewDog("Dermot Paluduz", "Scottish Deerhound", GenderType.MAN, " ČMKU D 53/08", "2006-08-28",	"Olga Brandová");
    createNewDog("Hyndsight Rebus", "Scottish Deerhound", GenderType.MAN, "", "2008-04-02", "Olga Brandová");
    createNewDog("Assing Irater Irsias", "Scottish Deerhound", GenderType.MAN, "ČMKU D 55/09", "2008-02-13", "Eva a Hana Voborníková");
    createNewDog("Canterville Margodeer", "Scottish Deerhound", GenderType.MAN, "ČMKU D 960/09", "2006-10-25",	"Igor Bělský");
    createNewDog("Gambo Ekibondo", "Basenji", GenderType.MAN, "ČMKU/BSN/888/08", "2006-12-16", "Ivana Fialová a Marek Leš");
    createNewDog("Ankan Ningen Banken", "Whippet", GenderType.MAN, "939/08", "2006-12-16", "Novotný Martin + Miluše");
    createNewDog("Quickstep z Hedvábí", "Whippet", GenderType.MAN, "", "2008-03-23", "Renata Kadlecová");
    createNewDog("Lúthien z Hedvábí", "Whippet", GenderType.WOMAN, "ČMKU/Wh/844/07", "2005-04-07", "Helena Stohanzlová");
    createNewDog("Tiszaparti Szélvész Csalfa", "Whippet", GenderType.WOMAN, "031/08", "2007-11-28", "Illés Edit");
    createNewDog("Egon od Hněvína", "Whippet", GenderType.MAN, "CMKU WH 918/08", "2006-10-17", "Buchal Vítězslav");
    createNewDog("Doubravka od Hněvína", "Whippet", GenderType.WOMAN, "CMKU WH 795/08", "2005-07-23", "Beránková Stanislava");
    createNewDog("Nefertiti z Úplňku", "Whippet", GenderType.WOMAN, "CMKU WH 870/07", "2005-08-24", "Beránková Stanislava");
    createNewDog("Gigi od Hněvína", "Whippet", GenderType.MAN, "", "2008-05-06", "Beránková Stanislava");
    createNewDog("Hanny od Hněvína", "Whippet", GenderType.WOMAN, "", "2009-03-29", "Luboš Lohr");
    createNewDog("Gracie-Blue ze Zličínských luk", "Afghan Hound", GenderType.WOMAN, "ČMKU AF 977/09", "2009-03-29", "Alena Mačíová");
    createNewDog("Císařovna ze Zličínských luk", "Afghan Hound", GenderType.WOMAN, "ČMKU AF 874/07", "2004-10-15", "Alena Mačíová");
    createNewDog("Jagar z Hedvábí", "Whippet", GenderType.MAN, "WH 658/05", "2002-07-29", "Marketa Kalousková");
    createNewDog("Saxana z Hedvábí", "Whippet", GenderType.WOMAN, "", "2009-05-23", "Marketa Kalousková");
    createNewDog("Breathles Mahoney Stohe", "Whippet", GenderType.MAN, "Wh125/10", "2007-09-07", "Petra Pařízková");
    createNewDog("Bugatti Blue Jeans Sunny Funny", "Whippet", GenderType.WOMAN, "ČMKU WH 165/10", "2009-05-02", "Jana Herinková");
    createNewDog("Body-Painting Smile Sunny Funny", "Whippet", GenderType.WOMAN, "ČMKU WHI 164/10", "2009-05-02", "Jana Herinková");
    createNewDog("Anthony z Podlipnice", "Scottish Deerhound", GenderType.MAN, "ČMKU/D/127/10", "2008-04-12", "Ivana Kohoutová");
    createNewDog("Dag des Legendes du Moyen Age", "Greyhound", GenderType.MAN, "ČMKU G 72/09", "2008-01-24", "Kristýna Pašková");
    createNewDog("Claire Blue Lásky dar", "Whippet", GenderType.WOMAN, "ČMKU WH 684/05", "2003-05-29", "Michaela a Robert Sobčákovi");
    createNewDog("Annabelle Deixes", "Whippet", GenderType.WOMAN, "ČMKU WH 148/10", "2007-11-21", "Michaela a Robert Sobčákovi");
    createNewDog("Aragorn Deixes", "Whippet", GenderType.MAN, "ČMKU WH 14/09", "2007-11-21", "Michaela a Robert Sobčákovi");
    createNewDog("Anushka from Philaia", "Rhodesian Ridgeback", GenderType.WOMAN, "ČMKU/RR/118/04", "2003-01-12", "Pavla Brettová");
    createNewDog("Allegro Dancer Earl´s Legend", "Borzoi", GenderType.MAN, "ČMKU  B 959/09", "2006-02-27", "Eva a Žaneta Pokorná");
    createNewDog("Giggles Balvert´s von der Oelmühle", "Scottish Deerhound", GenderType.MAN, "CMKU D 103/10", "2008-09-08", "Eva a Žaneta Pokorná");
    createNewDog("Century Margodeer", "Scottish Deerhound", GenderType.WOMAN, "ČMKU D 963/09", "2006-10-25", "Zuzana Máderová");
    createNewDog("Desmond Fontanesia", "Rhodesian Ridgeback", GenderType.MAN, "349", "2006-03-14", "Nikol Venhrynovyčov");
    createNewDog("Daughter of Caen Morhen", "Irish Wolfhound", GenderType.WOMAN, "CMKU IW 163/10", "2009-01-01", "Romana a Jaromír Vraných");
    createNewDog("Easy Runner Himalaya", "Irish Wolfhound", GenderType.WOMAN, "ČMKU/IW/105/10", "2008-08-25", "Jana Műllerová");
    createNewDog("Darkness Flintstone", "Irish Wolfhound", GenderType.WOMAN, "ČMKU/IW/104/10", "2008-06-26", "Jana Műllerová");
    createNewDog("Druid Flintstone", "Irish Wolfhound", GenderType.WOMAN, "ČMKU/IW/130/10", "2008-08-26", "Ladislav Škarytka");
    createNewDog("Paloma FI-IT", "Borzoi", GenderType.WOMAN, "ČMKU B 134/10", "2008-06-14", "Barbora Slováčková");
    createNewDog("Romana FI-IT", "Borzoi", GenderType.WOMAN, "", "2008-10-28", "Barbora Slováčková");
    createNewDog("Sonja FI-IT", "Borzoi", GenderType.WOMAN, "", "2008-11-06", "Barbora Slováčková");
    createNewDog("Isoke Hanáček", "Basenji", GenderType.MAN, null, null, "Alena Jozífová");
    createNewDog("London´s fog Brawny Ironman","Whippet", GenderType.MAN, "ČMKU D 55/09", "2008-02-13", "Eva a Hana Voborníková");
    createNewDog("London´s fog Brawny Ironman","Whippet", GenderType.MAN, "SKJ 0285", "2007-11-13", "Zuzana Biskupská");
    createNewDog("Atri Elkessan","Azawagh", GenderType.WOMAN, "SKJ 0285", "2008-03-10", "Dana Kupková");
    createNewDog("Azira Yrtep","Saluki", GenderType.WOMAN, "ČMKU SA 945/08 ", "2006-07-27", "Petra Pudová");
    createNewDog("Bagheera Yrtep","Saluki", GenderType.WOMAN, "ČMKU SA 971/09", "2007-03-20", "Petra Pudová");
    createNewDog("Elmas Nadir Danone","Saluki", GenderType.MAN, "CMKU/SA/686/08", "2008-03-18", "Jana Jarolímová");
    createNewDog("Khalils Ubayy","Saluki", GenderType.MAN, "CMKU/SA/615/-08/08", "2008-02-05", "Vladislava Hradecká");
    createNewDog("Yaris del Monte de Haya","Saluki", GenderType.WOMAN, "CMKU/SA/586/04", null, "Gabriela Holánková");
    createNewDog("Emira Parsa Danone","Saluki", GenderType.WOMAN, "CMKU/SA/691/08", null, "Gabriela Holánková");
    createNewDog("Epic Historical","Greyhound", GenderType.WOMAN, "", "2008-07-13", "Jaroslava Marková");
    createNewDog("Eve z Netluckých pastvin","Afghan Hound", GenderType.WOMAN, "ČMKU AF 139/1", "2008-07-25", "Petra Tůmová");
    createNewDog("Lorrequer Kamal Will al-Quadar","Saluki", GenderType.MAN, "ČMKU SA 997/09", "2007-05-21", "Veronika Šiklová");
    createNewDog("Alpine Diesel Sunny Funny","Whippet", GenderType.MAN, "ČMKU WH 992/ 09", "2007-12-23", "Ľubica Toušová");
    createNewDog("Agassi Pibaro","Whippet", GenderType.MAN, "ČMKU WH 28/09", "2003-07-02", "Tereza Pokorná");
    createNewDog("Yamal del Monte de Haya","Saluki", GenderType.MAN, "ČMKU SA 26/09", "2004-01-09", "Tereza Pokorná");
    createNewDog("Beauty In My Eyes Bohmar","Greyhound", GenderType.WOMAN, "ČMKU G 960/08", "2006-12-13", "Eva a Radovana Dufková");
    createNewDog("Body z Murky","Borzoi", GenderType.MAN, "ČMKU/B/864/07", "2006-03-15", "Pavlína Pašková");
    createNewDog("Romanov z Palatinu Moravia","Borzoi", GenderType.MAN, "ČMKU/B/100/04", "2002-07-29", "Pavlína Pašková");
    createNewDog("Dancing Lady Windmeister´s","Whippet", GenderType.WOMAN, "", "2007-01-21", "Veronika Frolíková");
    createNewDog("Erzena Blue Lásky dar","Whippet", GenderType.WOMAN, "", null, "Veronika Frolíková");
    createNewDog("Expressiv Hort Ezhevika","Hortoya Borzaya", GenderType.WOMAN, "", "2009-03-11", "Vaclav Cigler");
    createNewDog("Florance Globe Glass","Whippet", GenderType.WOMAN, "CMKU/Wh/899/08", "2006-04-11", "Ctirad Charvát");
  }

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private void createNewDog(String name, String breed, GenderType genderType, String licence, String birthdate, String owner) {
    Dog dog = dogService.createNew();
    dog.setName(name);
    dog.setGenderType(genderType);
    dog.setLicenceNumber(licence);
    dog.setOwner(owner);
    if (birthdate == null) {
      dog.setBirthdate(null);
    } else {
      try {
        dog.setBirthdate(DATE_FORMAT.parse(birthdate));
      } catch (ParseException e) {
        LOG.error("Problem with parse date: " + birthdate, e);
      }
    }
    dog.setBreed(breedService.breedByName(breed));
    dogService.save(dog);
  }
}
