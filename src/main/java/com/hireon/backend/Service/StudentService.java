package com.hireon.backend.Service;

import com.hireon.backend.Model.Student;
import com.hireon.backend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(Student student) {
        System.out.println("inside service");
        return studentRepo.save(student);
    }

    public Student getStudent(long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    public Student updateStudent(Student student) {

        Student updateObj = getStudent(student.getS_id());

        updateObj.setReg_no(student.getReg_no());
        updateObj.setName(student.getName());
        updateObj.setDepartment(student.getDepartment());
        updateObj.setBatch_year(student.getBatch_year());
        updateObj.setRole(student.getRole());
        updateObj.setCgpa(student.getCgpa());
        updateObj.setIs_alumni(student.getIs_alumni());
        updateObj.setActive_arrear(student.getActive_arrear());
        updateObj.setHistory_of_arrear(student.getHistory_of_arrear());
        updateObj.setResume_url(student.getResume_url());
        updateObj.setLinkedin_url(student.getLinkedin_url());
        updateObj.setPlacement_status(student.getPlacement_status());
        updateObj.setComp_id(student.getComp_id());
        return studentRepo.save(updateObj);
    }

    public Student updateStatus(Long id, String status, long compId) {
        Student updateObj = getStudent(id);
        updateObj.setPlacement_status(status);
        updateObj.setComp_id(compId);
        return studentRepo.save(updateObj);
    }

    public String deleteStudent(long id) {
        studentRepo.deleteById(id);
        return "Student with id " + id + " has been deleted";
    }
}
