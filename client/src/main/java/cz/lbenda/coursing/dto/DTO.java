/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
