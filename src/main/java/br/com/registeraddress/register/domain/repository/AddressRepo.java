package br.com.registeraddress.register.domain.repository;

import br.com.registeraddress.register.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {


    Optional<Address> findByUserId(Integer id);
}
