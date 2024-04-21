package repository.professorRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import model.Professor;
import org.hibernate.SessionFactory;
import repository.personRepository.PersonRepository;

public class ProfessorRepositoryImpl extends BaseRepositoryImpl<Professor,Long> implements ProfessorRepository {


    public ProfessorRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Professor> getEntityClass() {
        return null;
    }
}
