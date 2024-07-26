package services;

import dao.OwnerDAO;

public class OwnerService {
    OwnerDAO ownerDAO;

    public OwnerDAO getOwnerDAO() {
        return ownerDAO;
    }

    public void setOwnerDAO(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }
}
