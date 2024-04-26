package service.professorService;

import base.service.BaseService;
import model.Professor;

import java.util.Optional;

public interface ProfessorService extends BaseService<Professor, Long> {
    public Optional<Professor> professorSignIn(String nationalId, String password);
    public Optional<Professor> professorInfo(String nationalId);
    boolean checkNationalIdAndPassword(String nationalId, String password);
}
