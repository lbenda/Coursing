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

import cz.lbenda.coursing.dto.Judge;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Entity(name = "Judge")
public class JudgeImpl extends DTOImpl implements Judge {

    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 1024)
    private String comment;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
