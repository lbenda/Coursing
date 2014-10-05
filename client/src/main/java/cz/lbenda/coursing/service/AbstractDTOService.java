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
package cz.lbenda.coursing.service;

import cz.lbenda.coursing.dto.DTO;
import java.util.List;

/** Abstract parent for service which give accest o DTO objects
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
public abstract interface AbstractDTOService<T extends DTO> {

  /** Listener which is inform when data of DTo is changed */
  public interface DTOChangedListener<E extends DTO> {
    /** Is called when DTO is changed
     * @param oldDTO old value of DTO (null if new DTO is created) }
     * @param newDTO new value of DTO
     */
    void dtoChanged(E oldDTO, E newDTO);
  }

  /** This method return all entities of type which is served by service
   * @return return all entities
   * @throws java.lang.UnsupportedOperationException some service didn't support this operation.
   */
  List<T> allEntities() throws UnsupportedOperationException;

  /** Count of all entities in database
   * @return count of entities
   */
  long count();

  /** Method return object by identifier
   * @param id identifier of object (mustn't be null)
   * @return object by ID, or null
   */
  T getById(String id);

  /** Save given entity
   * @param entity to save
   */
  void save(T entity);

  /** Return new instance of entity object
   * @return new created object
   * @throws UnsupportedOperationException no all service support this method
   */
  T createNew() throws UnsupportedOperationException;

  void addDTOChangedListener(DTOChangedListener<T> l);
  void removeDTOChangedListener(DTOChangedListener<T> l);
}
