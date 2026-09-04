package com.hireon.backend.Controller;

import com.hireon.backend.Model.Student;
import com.hireon.backend.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getnull")
    public Student getNull(){
        return new Student();
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        log.info("inside controller");
        return studentService.addStudent(student);
    }

    @GetMapping("/Id/{id}")
    public Student getStudent(@PathVariable long id){
        return studentService.getStudent(id);
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/Name/{name}")
    public Optional<Student> getStudentByName(@PathVariable String name){
        return studentService.getStudentByName(name);
    }

    @PutMapping("/Update")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @PutMapping("/Status")
    public Student updateStatus(@RequestParam Long id,String status,long comp_id){
        return studentService.updateStatus(id,status,comp_id);
    }

    @DeleteMapping("/Delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

}
