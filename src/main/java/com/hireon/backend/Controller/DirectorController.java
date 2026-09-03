package com.hireon.backend.Controller;

import com.hireon.backend.Model.Director;
import com.hireon.backend.Service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/director")

public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @PostMapping("/register")
    public Director register (@RequestBody Director director){
        return directorService.register(director);
    }

//    @PostMapping("/login")
//    public  login (){
//
//    }
//
//    @GetMapping("/{id}")
//    public  getDirector (){
//
//    }
//
//    @PutMapping("/{id}")
//    public  updateDirector

}
