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
package cz.lbenda.coursing.server;

import cz.lbenda.coursing.dto.DTO;
import cz.lbenda.coursing.service.AbstractDTOService;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
public abstract class AbstractDTOServiceImpl<T extends DTO> implements AbstractDTOService<T> {

  protected abstract JpaRepository<T, String> repository();

  @Override
  @Transactional(readOnly = true)
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public List<T> allEntities() throws UnsupportedOperationException { return repository().findAll(); }

  @Override
  @Transactional(readOnly = true)
  public long count() {
    return repository().count();
  }

  @Override
  @Transactional(readOnly = true)
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  public T getById(String id) { return repository().getOne(id); }

  @Override
  @Transactional(readOnly = false)
  @Secured("ROLE_ADMIN")
  public void save(T entity) {
    Assert.notNull(entity);
    T oldDTO = null;
    if (entity.getId() == null) { entity.generateId(); }
    else { oldDTO = getById(entity.getId()); }
    repository().save(entity);
    fireDTOChanges(oldDTO, entity);
  }

  @Override
  @Transactional(readOnly = false)
  @Secured("ROLE_ADMIN")
  public void delete(T entity) {
    if (entity == null) { return; }
    repository().delete(entity);
    fireDTOChanges(entity, null);
  }

  @Override
  @Transactional(readOnly = false)
  @Secured("ROLE_ADMIN")
  public void delete(T[] entities) {
    if (entities == null || entities.length == 0) { return; }
    for (T entity : entities) { repository().delete(entity); }
    for (T entity : entities) { fireDTOChanges(entity, null); }
  }

  @Override
  public T createNew() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private final Set<DTOChangedListener<T>> dtoChangeListener
          = Collections.newSetFromMap(new WeakHashMap<DTOChangedListener<T>, Boolean>());

  /** Fire event the DTO is changed {@see AbstractDTOService.DTOChangedListener} */
  protected final void fireDTOChanges(T oldDTO, T newDTO) {
    for (DTOChangedListener l : dtoChangeListener) {
      l.dtoChanged(oldDTO, newDTO);
    }
  }

  @Override
  public void addDTOChangedListener(DTOChangedListener<T> l) {
    dtoChangeListener.add(l);
  }
  @Override
  public void removeDTOChangedListener(DTOChangedListener<T> l) {
    dtoChangeListener.remove(l);
  }
}
