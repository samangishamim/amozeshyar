package repository.professorRepository;

import base.repository.BaseRepository;
import model.Person;
import model.Professor;

import java.util.Optional;

public interface ProfessorRepository extends BaseRepository<Professor, Long> {
    public Optional<Professor> professorSignIn(String nationalId , String password);
    public Optional<Professor> professorInfo(String nationalId);
}
