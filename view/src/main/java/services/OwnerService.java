package services;

import dao.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class OwnerService {
    OwnerDAO ownerDAO;

    public OwnerDAO getOwnerDAO() {
        return ownerDAO;
    }

    @Autowired
    public void setOwnerDAO(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }
}
