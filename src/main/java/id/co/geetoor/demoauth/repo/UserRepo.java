package id.co.geetoor.demoauth.repo;

import id.co.geetoor.demoauth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findOneByEmailIgnoreCaseAndPassword(String emailId, String password);

}
