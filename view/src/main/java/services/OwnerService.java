package services;

import exceptions.ObjectNotFoundException;
import models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.OwnerRepository;

@Service
@Component
public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerRepository getOwnerRepository() {
        return ownerRepository;
    }

    public void createOwner(Owner owner) {
        ownerRepository.save(owner);
    }
    public Owner getOwnerById(long id) throws ObjectNotFoundException {
        return ownerRepository.getReferenceById(id);
    }

    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner); //?
    }
    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
