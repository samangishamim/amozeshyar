package repository.professorRepository;

import base.repository.BaseRepositoryImpl;
import model.Employee;
import model.Person;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.personRepository.PersonRepository;

import java.util.List;
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
    public Optional<List<Professor>> professorSignIn(String nationalId, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql ="FROM Professor p WHERE p.nationalId=:nationalId AND p.password=:password ";
        Query<Professor> query = session.createQuery(hql , Professor.class);
        query.setParameter("nationalId", nationalId );
        query.setParameter( "password" , password );
        List<Professor> resultList = query.getResultList();

        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<Professor> professorInfo(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Professor> query = session.createQuery("FROM Professor p WHERE p.nationalId = :theNationalId", Professor.class);
        List<Professor> resultList = query.setParameter("theNationalId", nationalId).getResultList();
        if (resultList.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }


}
