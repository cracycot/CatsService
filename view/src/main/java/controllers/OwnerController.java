package controllers;


import dao.OwnerDAO;
import models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.OwnerService;

import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    OwnerService ownerService;

    @GetMapping("/get")
    public ResponseEntity<?> getOwnerById(@RequestParam Integer id) {
        try {
            Owner owner = ownerService.getOwnerById(id);
            if (owner == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Владелец не найден");
            }
            return ResponseEntity.ok().body(owner);
        } catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createOwner(@RequestBody Owner owner) {
        try {
            ownerService.createOwner(owner);
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }

    }

    @GetMapping("/delete")
    public ResponseEntity<?>  deleteOwner(@RequestParam Integer id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.ok().body("Владелец удален");
        }
        catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>  updateOwner(@RequestBody Owner owner) {
        try {
            ownerService.updateOwner(owner);
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
