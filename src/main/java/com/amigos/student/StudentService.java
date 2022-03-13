package com.amigos.student;

import com.amigos.student.exception.BadRequestException;
import com.amigos.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public void add(Student student) {
        boolean emailExists = this.studentRepository.checkIfEmailExists(student.getEmail());
        if (emailExists) {
            throw new BadRequestException("Email: " + student.getEmail() + " already taken.");
        }

        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!this.studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Stundent with id: " + studentId + " does not exists");
        }

        this.studentRepository.deleteById(studentId);
    }

}
