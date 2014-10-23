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

import cz.lbenda.coursing.dto.NumberLine;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class NumberLineImpl extends DTOImpl implements NumberLine {

  @Column(length = 60, unique = true, nullable = false)
  private String name;
  @Column(length = 1024)
  private String comment;
  @Column
  private int sort;

  public @Override String getName() { return name; }
  public @Override void setName(String name) { this.name = name; }

  public @Override String getComment() { return comment; }
  public @Override void setComment(String comment) { this.comment = comment; }

  public @Override int getSort() { return sort; }
  public @Override void setSort(int sort) { this.sort = sort; }

  public @Override String toString() { return getName(); }
}
