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

import cz.lbenda.coursing.dto.Dog;
import cz.lbenda.coursing.server.AbstractDTOServiceImpl;
import cz.lbenda.coursing.server.dto.DogImpl;
import cz.lbenda.coursing.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Service("DogService")
public class DogServiceImpl  extends AbstractDTOServiceImpl<Dog> implements DogService {

  @Autowired(required = true)
  private DogRepository repository;

  @Override
  protected JpaRepository<Dog, String> repository() {
    return (JpaRepository<Dog, String>) (Object) repository;
  }

  @Override
  @Transactional(readOnly = false)
  @Secured({"ROLE_USER"})
  public void save(Dog entity) {
    super.save(entity);
  }

  @Override
  public Dog createNew() throws UnsupportedOperationException {
    DogImpl result = new DogImpl();
    fireDTOChanges(null, result);
    return result;
  }
}
