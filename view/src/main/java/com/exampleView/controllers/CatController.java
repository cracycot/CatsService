package com.exampleView.controllers;


import com.exampleLogic.dao.CatDAO;
import com.exampleLogic.dto.CatDTO;
import com.exampleLogic.models.Cat;
import com.exampleView.services.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {
    private static final Logger log = LoggerFactory.getLogger(CatDAO.class);
    com.exampleView.services.CatService catService;


    @GetMapping("/get")
    public ResponseEntity<?> getCatById(@RequestParam Long id) {
        try {
            Optional<Cat> catOptional = catService.getCatById(id);
            if (!catOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Владелец не найден");
            }
            Cat cat = catOptional.get();
            return ResponseEntity.ok().body(catService.fromCatToCatDTO(cat));
        } catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
    @GetMapping("")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body("доступ получен");
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createCat(@RequestBody CatDTO catDTO) {
        try {
            catService.createCat(catService.fromCatDTOToCat(catDTO));
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }

    }

    @GetMapping("/delete")
    public ResponseEntity<?>  deleteCat(@RequestParam Long id) {
        try {
            catService.deleteCat(id);
            return ResponseEntity.ok().body("Владелец удален");
        }
        catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>  updateCat(@RequestBody CatDTO catDTO) {
        try {
            catService.updateCat(catService.fromCatDTOToCat(catDTO));
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
             log.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    public com.exampleView.services.CatService getCatService() {
        return catService;
    }

    @Autowired
    public void setCatService(CatService CatService) {
        this.catService = CatService;
    }

}