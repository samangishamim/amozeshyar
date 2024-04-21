package repository.studentRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import model.Student;
import org.hibernate.SessionFactory;
import repository.personRepository.PersonRepository;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student,Long> implements StudentRepository {
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return null;
    }
}
