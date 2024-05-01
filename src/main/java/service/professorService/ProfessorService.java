package service.professorService;

import base.service.BaseService;
import model.Employee;
import model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService extends BaseService<Professor, Long> {
    public List<Professor> professorSignIn(String nationalId, String password);
    public Optional<Professor> professorInfo(String nationalId);
    boolean checkNationalIdAndPassword(String nationalId, String password);
}
