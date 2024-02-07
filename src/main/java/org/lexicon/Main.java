package org.lexicon;

import org.lexicon.config.ComponentScanConfig;
import org.lexicon.config.ScannerConfig;
import org.lexicon.data_access.StudentDao;
import org.lexicon.service.UserInputService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
        AnnotationConfigApplicationContext scannerContext = new AnnotationConfigApplicationContext(ScannerConfig.class);
        UserInputService userInputService =scannerContext.getBean(UserInputService.class);

//        studentDao.save(new Student(1, "Mjau"));
        System.out.println(studentDao.find(1));

        System.out.println("Update student");


    }
}
