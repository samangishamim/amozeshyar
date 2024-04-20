package service.professorService;

import base.service.BaseServiceImpl;
import model.Professor;
import org.hibernate.SessionFactory;
import repository.professorRepository.ProfessorRepository;

public class ProfessorServiceImpl extends BaseServiceImpl<Professor, Long, ProfessorRepository> implements ProfessorService{
    public ProfessorServiceImpl(ProfessorRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
