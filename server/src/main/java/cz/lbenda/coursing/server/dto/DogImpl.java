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

import cz.lbenda.coursing.common.GenderType;
import cz.lbenda.coursing.dto.Breed;
import cz.lbenda.coursing.dto.Dog;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
@Entity(name = "Dog")
public class DogImpl extends DTOImpl implements Dog {

    @Column(length=150)
    private String name;
    @ManyToOne(targetEntity = BreedImpl.class)
    @JoinColumn(name="Breed_id")
    private Breed breed;
    @Enumerated(EnumType.STRING)
    private GenderType genderType;
    @Column(length=50)
    private String licenceNumber;
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(length=1024)
    private String comment;
    @Column(length=1024)
    private String owner;

    public @Override String getName() { return name; }
    public @Override void setName(String name) { this.name = name; }

    public @Override Breed getBreed() { return breed; }
    public @Override void setBreed(Breed breed) { this.breed = breed; }

    public @Override GenderType getGenderType() { return genderType; }
    public @Override void setGenderType(GenderType genderType) { this.genderType = genderType; }

    public @Override String getLicenceNumber() { return licenceNumber; }
    public @Override void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }

    public @Override Date getBirthdate() { return birthdate; }
    public @Override void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public @Override String getComment() { return comment; }
    public @Override void setComment(String comment) { this.comment = comment; }

    public @Override String getOwner() { return owner; }
    public @Override void setOwner(String owner) { this.owner = owner; }
}
