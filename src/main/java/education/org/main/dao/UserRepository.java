package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import education.org.main.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
