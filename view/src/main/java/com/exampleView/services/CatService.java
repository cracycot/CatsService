package com.exampleView.services;

import com.exampleLogic.dao.CatDAO;
import com.exampleLogic.dto.CatDTO;
import com.exampleLogic.exceptions.ObjectNotFoundException;
import com.exampleLogic.models.Cat;
import com.exampleLogic.models.Owner;
import com.exampleLogic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exampleLogic.repositories.CatRepository;

import java.util.*;

//@Service
@Service
public class CatService {

    OwnerRepository ownerRepository;

    CatRepository catRepository;

    public CatRepository getCatRepository() {
        return catRepository;
    }


    public Optional<Cat> getCatById(Long id) throws ObjectNotFoundException {
        return catRepository.findById(id);
    }

    public void createCat(Cat cat) {
        catRepository.save(cat);
    }

    public void updateCat(Cat cat) {
        catRepository.save(cat);
    }

    public void deleteCat(Long id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        if (catOptional.isPresent()) {
            Cat cat = catOptional.get(); // Получаем объект Cat
            catRepository.delete(cat); // Удаляем кота
        } else {
            throw new RuntimeException("Cat not found with id: " + id);
        }
    }
    public CatDTO fromCatToCatDTO(Cat cat) {
        Set<Long> friendsId = new HashSet<>();

        for (Cat friend : cat.getFriends()) {
            friendsId.add((long) friend.getId());
        }

        return new CatDTO.Builder()
                .name(cat.getName())
                .dateBirth(cat.getDateBirth())
                .breed(cat.getBreed())
                .ownerId((long) cat.getOwner().getId())
                .friendsId(friendsId)
                .id((long) cat.getId())
                .build();
    }

    public Cat fromCatDTOToCat(CatDTO catDTO){
        Optional<Owner> ownerOptional = ownerRepository.findById(catDTO.getOwnerId());
        if (!ownerOptional.isPresent()) {
            throw new RuntimeException("Владелец не найден!");
        }
        Owner owner = ownerOptional.get();
        Set<Cat> friends = new HashSet<>();
        for (Long key : catDTO.getFriendsId()) {
            Optional<Cat> catOptional = catRepository.findById(key);
            if (!catOptional.isPresent()) {
                throw new RuntimeException("Кот не найден!");
            }
            friends.add(catOptional.get());
        }

        return new Cat.Builder()
                .name(catDTO.getName())
                .dateBirth(catDTO.getDateBirth())
                .breed(catDTO.getBreed())
                .owner(owner)
                .friends(friends)
                .id(catDTO.getId().intValue())
                .build();
    }

    @Autowired
//    @Qualifier("CatRepository")
   // @Qualifier|("catDAO") //уточненине для спринга если наследуемый интерфейс имеет две реализации в бинах
    public void setCatRepository(CatRepository catRepository ) {
        this.catRepository = catRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository ) {
        this.ownerRepository = ownerRepository;
    }

}
