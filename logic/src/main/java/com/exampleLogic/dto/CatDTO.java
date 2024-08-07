package com.exampleLogic.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CatDTO {

    private String name;

    private LocalDate dateBirth;

    private String breed;

    private Long ownerId;

    private Set<Long> friendsId = new HashSet<>();

    private Long id;

    public static class Builder {
        private static CatDTO catDTO = new CatDTO();

        public Builder name(String name) {
            catDTO.name = name;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            catDTO.dateBirth = dateBirth;
            return this;
        }

        public Builder breed(String breed) {
            catDTO.breed = breed;
            return this;
        }

        public Builder ownerId(Long ownerId) {
            catDTO.ownerId = ownerId;
            return this;
        }

        public Builder friendsId(Set<Long> friendsId) {
            catDTO.friendsId = friendsId;
            return this;
        }

        public Builder id(Long id) {
            catDTO.id = id;
            return this;
        }

        public CatDTO build() {
            return catDTO;
        }


    }

    public CatDTO() {

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<Long> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Set<Long> friendsId) {
        this.friendsId = friendsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
