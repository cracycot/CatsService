package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.CatService;

@RestController
@RequestMapping("/Cat")
public class CatController {
    CatService CatService;

    @GetMapping("/get")
    public void getCatById() {

    }

    @PostMapping("/create")
    public void createCat() {

    }

    @GetMapping("/delete")
    public void deleteCat() {

    }

    @PutMapping("/update")
    public void updateCat() {

    }

    @Autowired
    public void setCatService(CatService CatService) {
        this.CatService = CatService;
    }

}
