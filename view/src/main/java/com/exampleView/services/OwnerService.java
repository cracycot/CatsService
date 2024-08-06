package com.exampleView.services;

import com.exampleLogic.dao.OwnerDAO;
import com.exampleLogic.exceptions.ObjectNotFoundException;
import com.exampleLogic.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    OwnerDAO ownerDAO;

    public OwnerDAO getOwnerDAO() {
        return ownerDAO;
    }

    public Owner getOwnerById(Integer id) throws ObjectNotFoundException {
        return ownerDAO.read(id);
    }

    public void createOwner(Owner owner) {
        ownerDAO.create(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDAO.update(owner);
    }

    public void deleteOwner(Integer id) {
        ownerDAO.remove(id);
    }
    @Autowired
    public  OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }
}
