package services;

import dao.CatDAO;
import dao.DAO;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
@Service
public class CatService {

    CatDAO catDAO;

    public CatDAO getCatDAO() {
        return catDAO;
    }

    @Autowired
   // @Qualifier("catDAO") //уточненине для спринга если наследуемый интерфейс имеет две реализации в бинах
    public void setCatDAO(CatDAO catDAO  ) throws ObjectNotFoundException {
        this.catDAO = catDAO;
    }

}
