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
package cz.lbenda.coursing.report;

import cz.lbenda.coursing.client.ClientServiceLocator;
import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.service.DogService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.base.JRBaseField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogsJRDataSource implements JRDataSource {

  private static final Logger LOG = LoggerFactory.getLogger(DogsJRDataSource.class);

  static List<JRField> FIELDS = Arrays.asList(new JRField[] {
    new DogField("name", "name"),
    new DogField("breed", "breed"),
    new DogField("genderType", "genderType"),
    new DogField("licenceNumber", "licenceNumber"),
    new DogField("birthdate", "birthdate", Date.class),
    new DogField("comment", "comment"),
    new DogField("owner", "owner")
  });

  private DogService dogService;

  private List<Dog> dogs;
  private int counter;

  public DogsJRDataSource(DogService dogService) {
    this.dogService = dogService;
    // dogs = dogService.allEntities();
  }

  @Override
  public boolean next() throws JRException {
    LOG.debug("Next call");
    if (dogs == null) {
      dogs = dogService.allEntities();
    }

    if (counter < dogs.size() - 1) {
      counter++;
      LOG.debug("next: true");
      return true;
    }
    LOG.debug("next: false");
    return false;
  }

  @Override
  public Object getFieldValue(JRField jrf) throws JRException {
    Dog dog = dogs.get(counter);
    String name = jrf.getName();
    if ("name".equals(name)) { return dog.getName(); }
    if ("breed".equals(name)) { return dog.getBreed() == null ? "" : dog.getBreed().getName(); }
    if ("genderType".equals(name)) { return dog.getGenderType() == null ? "" : dog.getGenderType().name(); }
    if ("licenceNumber".equals(name)) { return dog.getLicenceNumber(); }
    if ("birthdate".equals(name)) { return dog.getBirthdate(); }
    if ("comment".equals(name)) { return dog.getComment(); }
    if ("owner".equals(name)) { return dog.getOwner(); }
    return "";
  }

  public static class DogField extends JRBaseField {
    public DogField(String name, String description, Class<?> type) {
      this.name = name;
      this.description = description;
      this.valueClass = type;
      this.valueClassName = type.getName();
    }
    public DogField(String name, String description) {
      this(name, description, String.class);
    }
  }

  void dispose() {
    dogs = null;
  }

  public static JRDataSource getDataSource() {
    return new DogsJRDataSource(ClientServiceLocator.getInstance().getBean(DogService.class));
  }
}
