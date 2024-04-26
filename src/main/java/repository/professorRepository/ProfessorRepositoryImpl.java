package repository.professorRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.personRepository.PersonRepository;

import java.util.Optional;

public class ProfessorRepositoryImpl extends BaseRepositoryImpl<Professor,Long> implements ProfessorRepository {


    public ProfessorRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Professor> getEntityClass() {
        return Professor.class;
    }

    @Override
    public String getMyClass() {
        return "Professor";
    }


    @Override
    public Optional<Professor> professorSignIn(String nationalId, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql ="FROM %s WHERE professorId=:professorId AND password=:password ";
        Query<Professor> query = session.createQuery(String.format(hql , getMyClass()),getEntityClass());
        query.setParameter("professorId", nationalId );
        query.setParameter( "password" , password );
        Object result = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Professor> professorInfo(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Professor> query = session.createQuery("FROM Professor p  " +
                " WHERE p.professorId=:professorId" , Professor.class);
        query.setParameter("professorId", nationalId );
        Professor professor = query.uniqueResult();

        return Optional.ofNullable(query.getSingleResult());
    }


}
