package com.exampleView.controllers;


import com.exampleLogic.dao.CatDAO;
import com.exampleLogic.dto.OwnerDTO;
import com.exampleLogic.models.Owner;
import com.exampleView.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private static final Logger log = LoggerFactory.getLogger(CatDAO.class);
    OwnerService ownerService;

    @GetMapping("/get")
    public ResponseEntity<?> getOwnerById(@RequestParam Long id) {
        try {
            Optional<Owner> ownerOptional = ownerService.getOwnerById(id);
            if (!ownerOptional.isPresent()) {
                log.error("Ошибка при получении владельца");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Владелец не найден");
            }
            Owner owner = ownerOptional.get();
            return ResponseEntity.ok().body(ownerService.fromOwnerToOwnerDTO(owner));
        } catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createOwner(@RequestBody OwnerDTO ownerDTO) {
        try {
            ownerService.createOwner(ownerService.fromOwnerDTOToOwner(ownerDTO));
            log.info("Владелец сохранен");
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
             log.error("Ошибка при сохранении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }

    }

    @GetMapping("/delete")
    public ResponseEntity<?>  deleteOwner(@RequestParam Long id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.ok().body("Владелец удален");
        }
        catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>  updateOwner(@RequestBody OwnerDTO ownerDTO) {
        try {
            ownerService.updateOwner(ownerService.fromOwnerDTOToOwner(ownerDTO));
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

}
