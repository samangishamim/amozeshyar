package repository.personRepository;

import base.repository.BaseRepository;
import model.Person;

public interface PersonRepository extends BaseRepository <Person,Long>{
    boolean signIn(String username, String password);
}
