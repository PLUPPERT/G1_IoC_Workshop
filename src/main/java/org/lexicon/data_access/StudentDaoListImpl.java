package org.lexicon.data_access;

import org.lexicon.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoListImpl implements StudentDao {
    List<Student> students = new ArrayList<>();

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student find(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void updateStudent(Student student) {
        for(Student s : students){
            if (s.getId()==student.getId()){
                s.setName(student.getName());
            }
        }
    }

    @Override
    public void deleteStudent(int id) {
        students.stream()
                .filter(student -> student.getId() == id)
                .findFirst().ifPresentOrElse(
                        student -> {
                            students.remove(student);
                            System.out.println("Student with id '" + id + "' were successfully deleted");
                        },
                        () -> System.out.println("No student with id '" + id + "' were found."));
        }
    }

