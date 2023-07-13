package net.zhenya.spring.rest.task3_1_3.repository;

import net.zhenya.spring.rest.task3_1_3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
