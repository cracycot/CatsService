package services;

import exceptions.ObjectNotFoundException;
import models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.CatRepository;

//@Service
@Component
@Service
public class CatService {

    CatRepository catRepository;

    public void createcat(Cat cat) {
        catRepository.save(cat);
    }
    public Cat getcatById(long id) throws ObjectNotFoundException {
        return catRepository.getReferenceById(id);
    }

    public void deletecat(Cat cat) {
        catRepository.delete(cat);
    }

    public void updatecat(Cat cat) {
        catRepository.save(cat); //?
    }


   // @Qualifier("catRepository") //уточненине для спринга если наследуемый интерфейс имеет две реализации в бинах
    public CatService(@Autowired CatRepository catRepository  ) throws ObjectNotFoundException {
        this.catRepository = catRepository;
    }

}
