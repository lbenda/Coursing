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

import cz.lbenda.coursing.dto.RaceType;
import cz.lbenda.coursing.server.AbstractDTOServiceImpl;
import cz.lbenda.coursing.server.dto.RaceTypeImpl;
import cz.lbenda.coursing.service.RaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Service("RaceTypeService")
public class RaceTypeServiceImpl  extends AbstractDTOServiceImpl<RaceType> implements RaceTypeService {

  @Autowired
  private RaceTypeRepository repository;

  @Override
  protected JpaRepository<RaceType, String> repository() {
    return (JpaRepository<RaceType, String>) (Object) repository;
  }

  @Override
  public RaceType createNew() throws UnsupportedOperationException {
    RaceTypeImpl result = new RaceTypeImpl();
    fireDTOChanges(null, result);
    return result;
  }
}
