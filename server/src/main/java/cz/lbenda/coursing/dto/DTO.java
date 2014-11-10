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
package cz.lbenda.coursing.dto;

import java.util.Date;

/** Interface for all DTO object which is in application
 * @author benzin
 */
public interface DTO {

    /** Return identifier of DTO object
     * @return identifier of DTO object
     */
    String getId();
    /** Set identifier of DTO object
     * @param id identifier of DTO object
     */
    void setId(String id);

    /** Method generate new ID and return it
     * @return new identifier of entity
     */
    String generateId();

    /** Identifier of user which create record
     * @return identifier of user
     */
    String getCreator();
    /** Identifier of user which create record
     * @param creator string identifier of user
     */
    void setCreator(String creator);

    /** Datetime when the record was created
     * @return date time when the record was created
     */
    Date getCreated();
     /** Datetime when the record was created
     * @param created date time when the record was created
     */
    void setCreated(Date created);

    /** User which record modified at last (the first user is creator)
     * @return user which modified record at last
     */
    String getModifier();
    /** User which record modified at last (the first user is creator)
     * @param modifier user which modified record at last
     */
    void setModifier(String modifier);

    /** Datetime when the record was modified at last (after created set to date of created)
     * @return date time when the record modified at last
     */
    Date getModified();
    /** Datetime when the record was modified at last (after created set to date of created)
     * @param modified date time when the record modified at last
     */
    void setModified(Date modified);
}
