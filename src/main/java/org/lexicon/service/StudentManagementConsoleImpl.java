package org.lexicon.service;

import org.lexicon.data_access.StudentDao;
import org.lexicon.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentManagementConsoleImpl implements StudentManagement {

    UserInputService scannerService;
    StudentDao studentDao;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService scannerService, StudentDao studentDao) {
        this.scannerService = scannerService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() {
        String name;
        int id;
        System.out.println("Create a new Student");
        // System.out.print("Enter the Student id: ");
        id = scannerService.getInt();
        //System.out.println("Enter student name: ");
        name = scannerService.getString();
        Student newStudent = new Student(id, name);
        return studentDao.save(newStudent);
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student find(int id) {
        return studentDao.find(id);
    }

    @Override
    public Student remove(int id) {
        Optional<Student> optStudent = Optional.of(studentDao.find(id));

        optStudent.ifPresentOrElse(
                student -> {
                    studentDao.deleteStudent(id);
                },
                () -> {
                    throw new IllegalArgumentException("No student with id '" + id + "' were found.");
                }
        );

        return optStudent.get();
    }

    @Override
    public List<Student> findAll() {
        return studentDao.getAllStudents();
    }

    @Override
    public Student edit(Student student) {
        Optional<Student> optStudent = Optional.of(studentDao.find(student.getId()));

        Student foundStudent = optStudent.orElse(null);

        if (foundStudent == null) throw new IllegalArgumentException("No student with id '" + student.getId() + "' were found.");
        
        studentDao.updateStudent(foundStudent);

        return foundStudent;
    }
}
