package net.zhenya.spring.rest.task3_1_3.repository;

import net.zhenya.spring.rest.task3_1_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstname(String username);

}
