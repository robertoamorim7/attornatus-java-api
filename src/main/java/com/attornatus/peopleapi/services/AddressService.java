package com.attornatus.peopleapi.services;

import com.attornatus.peopleapi.entities.Address;
import com.attornatus.peopleapi.entities.Person;
import com.attornatus.peopleapi.repositories.AddressRepository;
import com.attornatus.peopleapi.services.exceptions.DatabaseException;
import com.attornatus.peopleapi.services.exceptions.ResourceNotFoundException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public List<Address> findAll(Long id) {
        Optional<List<Address>> obj = repository.findAllByPersonId(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Address findPersonMainAddress(Long id) {
        try {
            return repository.findAllByPersonIdAndIsMainAddressTrue(id).iterator().next();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Address insert(Address obj, Long id) {
        boolean hasAddress = checkIfPersonHasAnyAddress(id);
        boolean hasMainAddress = checkIfPersonHasMainAddressAlready(id);
        if (!hasAddress || !hasMainAddress) {
            obj.setMainAddress(true);
            return repository.save(obj);
        }
        obj.setMainAddress(false);
        return repository.save(obj);
    }

    private boolean checkIfPersonHasAnyAddress(Long id) {
        List<Address> address = findAll(id);
        return address.size() > 0;
    }

    private boolean checkIfPersonHasMainAddressAlready(Long id) {
        Iterable<Address> mainAddress = repository.findAllByPersonIdAndIsMainAddressTrue(id);
        return mainAddress.iterator().hasNext();
    }


}
