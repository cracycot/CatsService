package com.exampleView.controllers;


import com.exampleLogic.models.Cat;
import com.exampleView.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {
    com.exampleView.services.CatService CatService;


    @GetMapping("/get")
    public ResponseEntity<?> getCatById(@RequestParam Long id) {
        try {
            Optional<Cat> catOptional = CatService.getCatById(id);
            if (!catOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Владелец не найден");
            }
            Cat cat = catOptional.get();
            return ResponseEntity.ok().body(cat);
        } catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
    @GetMapping("")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body("доступ получен");
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createCat(@RequestBody Cat cat) {
        try {
            CatService.createCat(cat);
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }

    }

    @GetMapping("/delete")
    public ResponseEntity<?>  deleteCat(@RequestParam Long id) {
        try {
            CatService.deleteCat(id);
            return ResponseEntity.ok().body("Владелец удален");
        }
        catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>  updateCat(@RequestBody Cat Cat) {
        try {
            CatService.updateCat(Cat);
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    public com.exampleView.services.CatService getCatService() {
        return CatService;
    }

    @Autowired
    public void setCatService(CatService CatService) {
        this.CatService = CatService;
    }

}