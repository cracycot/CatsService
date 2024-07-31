package controllers;


import models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CatService;

@RestController
@RequestMapping("/cats")
public class CatController {
    CatService CatService;
    @GetMapping("/get")
    public ResponseEntity<?> getCatById(@RequestParam Integer id) {
        try {
            Cat Cat = CatService.getCatById(id);
            if (Cat == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Владелец не найден");
            }
            return ResponseEntity.ok().body(Cat);
        } catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createCat(@RequestBody Cat Cat) {
        try {
            CatService.createCat(Cat);
            return ResponseEntity.ok().body("Владелец сохранен");
        }  catch (Exception e) {
//             logger.error("Ошибка при получении владельца", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }

    }

    @GetMapping("/delete")
    public ResponseEntity<?>  deleteCat(@RequestParam Integer id) {
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
    @Autowired
    public void setCatService(CatService CatService) {
        this.CatService = CatService;
    }

}
