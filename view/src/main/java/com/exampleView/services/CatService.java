package com.exampleView.services;

import com.exampleLogic.dao.CatDAO;
import com.exampleLogic.exceptions.ObjectNotFoundException;
import com.exampleLogic.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exampleLogic.repositories.CatRepository;

import java.util.Optional;

//@Service
@Service
public class CatService {

    CatRepository catRepository;

    public CatRepository getCatRepository() {
        return catRepository;
    }

    CatDAO catDAO;

    public CatDAO getCatDAO() {
        return catDAO;
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
    @Autowired
    public void setCatDAO(CatDAO catDAO) {
        this.catDAO = catDAO;
    }
    @Autowired
//    @Qualifier("CatRepository")
   // @Qualifier|("catDAO") //уточненине для спринга если наследуемый интерфейс имеет две реализации в бинах
    public void setCatRepository(CatRepository catRepository ) throws ObjectNotFoundException {
        this.catRepository = catRepository;
//        System.out.println(catRepository.findById(1l).get().getId());
    }

}
