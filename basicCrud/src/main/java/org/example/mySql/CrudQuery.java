package org.example.mySql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudQuery {

    private SessionFactory sessionFactory;

    public CrudQuery() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public String testConnection(){
        // Ensure that the session is closed after the test
        try (Session session = sessionFactory.openSession()) {
            return "Connection Success";
        }
    }

    public List<Student> viewAllStudents(){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("From Student", Student.class).list();
        }
    }

    public Student getParticularStudentById(int id){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    public String insertNewStudent(Student newStudent){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(newStudent);
            transaction.commit();
            return "Student Details Successfully Inserted";
        }
    }

    public String updateStudent(Student updatingStudent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(updatingStudent);
            transaction.commit();
            return "Student Details Updated";
        }
    }

    public String deleteStudent(Student deletingStudent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(deletingStudent);
            transaction.commit();
            return "Deleted";
        }
    }
}
