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
package cz.lbenda.coursing.server.service;

import cz.lbenda.coursing.dto.Breed;
import cz.lbenda.coursing.server.AbstractDTOServiceImpl;
import cz.lbenda.coursing.server.dto.BreedImpl;
import cz.lbenda.coursing.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/** BreedService implementation
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Service("BreedService")
public class BreedServiceImpl extends AbstractDTOServiceImpl<Breed> implements BreedService {

  @Autowired
  private BreedRepository repository;

  @Override
  protected JpaRepository<Breed, String> repository() {
    return (JpaRepository<Breed, String>) (Object) repository;
  }

  @Override
  public Breed createNew() throws UnsupportedOperationException {
    BreedImpl result = new BreedImpl();
    fireDTOChanges(null, result);
    return result;
  }
}
