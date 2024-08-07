package com.exampleView.services;


import com.exampleLogic.dto.OwnerDTO;
import com.exampleLogic.exceptions.ObjectNotFoundException;
import com.exampleLogic.models.Cat;
import com.exampleLogic.models.Owner;
import com.exampleLogic.models.Owner;
import com.exampleLogic.repositories.CatRepository;
import com.exampleLogic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OwnerService {
    OwnerRepository ownerRepository;
    CatRepository catRepository;

    public OwnerRepository getOwnerRepository() {
        return ownerRepository;
    }

    public Optional<Owner> getOwnerById(Long id) throws ObjectNotFoundException {
        return ownerRepository.findById(id);
    }

    public void createOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get(); // Получаем объект Owner
            ownerRepository.delete(owner); // Удаляем кота
        } else {
            throw new RuntimeException("Owner not found with id: " + id);
        }
    }

    public OwnerDTO fromOwnerToOwnerDTO(Owner Owner) {
        Set<Long> catsId = new HashSet<>();

        for (Cat friend : Owner.getCats()) {
            catsId.add((long) friend.getId());
        }

        return new OwnerDTO.Builder()
                .name(Owner.getName())
                .dateBirth(Owner.getDateBirth())
                .catsId(catsId)
                .id((long) Owner.getId())
                .build();
    }

    public Owner fromOwnerDTOToOwner(OwnerDTO OwnerDTO){
        Set<Cat> friends = new HashSet<>();
        for (Long key : OwnerDTO.getCatsId()) {
            Optional<Cat> catOptional = catRepository.findById(key);
            if (!catOptional.isPresent()) {
                throw new RuntimeException("Кот не найден!");
            }
            friends.add(catOptional.get());
        }

        return new Owner.Builder()
                .name(OwnerDTO.getName())
                .dateBirth(OwnerDTO.getDateBirth())
                .cats(friends)
                .id(OwnerDTO.getId().intValue())
                .build();
    }

    @Autowired
    public  OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }
}
