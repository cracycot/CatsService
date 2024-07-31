package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    OwnerService ownerService;

    @GetMapping("/get")
    public void getOwnerById(@RequestParam("id") Long id) {
        return;
    }

    @PostMapping("/create")
    public void createOwner() {

    }

    @GetMapping("/delete")
    public void deleteOwner() {

    }

    @PutMapping("/update")
    public void updateOwner() {

    }

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

}
