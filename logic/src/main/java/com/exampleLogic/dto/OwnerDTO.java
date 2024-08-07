package com.exampleLogic.dto;

import com.exampleLogic.models.Cat;
import com.exampleLogic.models.Owner;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OwnerDTO {
    private String name;
    private LocalDate dateBirth;

    private Long id;

    private Set<Long> catsId = new HashSet<>();

    public static class Builder {
        private static OwnerDTO ownerDTO = new OwnerDTO(); 

        public OwnerDTO.Builder name(String name) {
            ownerDTO.name = name;
            return this;
        }

        public OwnerDTO.Builder dateBirth(LocalDate dateBirth) {
            ownerDTO.dateBirth = dateBirth;
            return this;
        }
        public OwnerDTO.Builder catsId(Set<Long> catsId) {
            ownerDTO.catsId = catsId;
            return this;
        }

        public OwnerDTO.Builder id(Long id) {
            ownerDTO.id = id;
            return this;
        }

        public OwnerDTO build() {
            return ownerDTO;
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long>  getCatsId() {
        return catsId;
    }

    public void setCatsId(Set<Long> catsId) {
        this.catsId = catsId;
    }

}
