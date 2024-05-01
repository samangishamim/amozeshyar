package repository.professorRepository;

import base.repository.BaseRepository;
import model.Employee;
import model.Person;
import model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends BaseRepository<Professor, Long> {
    public Optional<List<Professor>> professorSignIn(String nationalId , String password);
    public Optional<Professor> professorInfo(String nationalId);
}
