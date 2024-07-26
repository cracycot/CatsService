package services;

import dao.CatDAO;

public class CatService {
    CatDAO catDAO;

    public CatDAO getCatDAO() {
        return catDAO;
    }

    public void setCatDAO(CatDAO catDAO) {
        this.catDAO = catDAO;
    }

}
