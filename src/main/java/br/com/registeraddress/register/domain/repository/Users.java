package br.com.registeraddress.register.domain.repository;

import br.com.registeraddress.register.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends JpaRepository<User, Integer> {
}
