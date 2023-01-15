package com.attornatus.peopleapi.repositories;

import com.attornatus.peopleapi.entities.Address;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<List<Address>> findAllByPersonId(Long id);

    Iterable<Address> findAllByPersonIdAndIsMainAddressTrue(Long id);

}
