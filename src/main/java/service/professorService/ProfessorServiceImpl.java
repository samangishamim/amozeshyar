package service.professorService;

import base.service.BaseServiceImpl;
import connection.SessionFactorySingleton;
import exception.NotFoundException;
import model.Person;
import model.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.professorRepository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

public class ProfessorServiceImpl extends BaseServiceImpl<Professor, Long, ProfessorRepository> implements ProfessorService{
    public ProfessorServiceImpl(ProfessorRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Optional<Professor> professorSignIn(String nationalId, String password) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Professor> find = repository.professorSignIn(nationalId, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Professor> professorInfo(String nationalId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Professor> find = repository.professorInfo(nationalId);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            session.getTransaction().commit();
            return find;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean checkNationalIdAndPassword(String nationalId, String password) {
        SessionFactory factory = SessionFactorySingleton.getInstance();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Query<Person> query = session.createQuery("FROM Professor p " +
                    "WHERE p.nationalId = :nId AND p.password = :pass");
            query.setParameter("nId", nationalId);
            query.setParameter("pass", password);
            List<Person> resultList = query.getResultList();

            session.getTransaction().commit();
            return resultList.isEmpty();
        }
    }
}
