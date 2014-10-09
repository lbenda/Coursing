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
package cz.lbenda.coursing.server.dto;

import cz.lbenda.coursing.dto.DTO;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@MappedSuperclass
public class DTOImpl implements DTO, Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(DTOImpl.class);

  @Id
  @Column(length = 36, nullable = false)
  private String id;
  @Column(length = 60, nullable = false)
  private String creator;
  @Column
  @Temporal(TIMESTAMP)
  private Date created;
  @Column(length = 60, nullable = false)
  private String modifier;
  @Column(nullable = false)
  @Temporal(TIMESTAMP)
  private Date modified;
  @Version
  private Long version;

  @PrePersist
  private void prePersist() {
    if (id == null) { generateId(); }
    LOG.debug("prePersist");
    if (creator == null) {
      creator = SecurityContextHolder.getContext().getAuthentication().getName();
      created = new Date();
    }
    modifier = SecurityContextHolder.getContext().getAuthentication().getName();
    modified = new Date();
  }

  @Override
  public String getId() { return id; }
  @Override
  public void setId(String id) { this.id = id; }

  @Override
  public String generateId() {
    this.id = UUID.randomUUID().toString();
    return id;
  }

  @Override
  public String getCreator() { return creator; }
  @Override
  public void setCreator(String creator) { this.creator = creator; }

  @Override
  public Date getCreated() { return created; }
  @Override
  public void setCreated(Date created) { this.created = created; }

  @Override
  public String getModifier() { return modifier; }
  @Override
  public void setModifier(String modifier) { this.modifier = modifier; }

  @Override
  public Date getModified() { return modified; }
  @Override
  public void setModified(Date modified) { this.modified = modified; }

  public Long getVersion() { return version; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DTOImpl dto = (DTOImpl) o;

    if (id == null) { return dto == this; }

    if (id != null ? !id.equals(dto.id) : dto.id != null) return false;
    return !(version != null ? !version.equals(dto.version) : dto.version != null);
  }

  @Override
  public int hashCode() {
    if (id == null) { return super.hashCode(); }
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (version != null ? version.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "{" +
        "id='" + id + '\'' +
        ", creator='" + creator + '\'' +
        ", created=" + created +
        ", modifier='" + modifier + '\'' +
        ", modified=" + modified +
        ", version=" + version +
        '}';
  }
}
