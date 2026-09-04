package com.hireon.backend.Service;

import com.hireon.backend.Model.Director;
import com.hireon.backend.Repository.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepo directorRepo;
    public Director register(Director director) {

        return directorRepo.save(director);
    }

    public Director getDirector(Long id) {
        return directorRepo.getById(id);
    }

    public Director updateDirector(Long id, Director request) {
        Director director=directorRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Director Not Found"));
        director.setName(request.getName());
        director.setEmail(request.getEmail());

        return directorRepo.save(director);
    }
}
