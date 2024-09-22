package de.scopevisio.vp.backend.data.store;


import de.scopevisio.vp.backend.data.entity.AddressEntity;
import de.scopevisio.vp.backend.data.model.Address;
import de.scopevisio.vp.backend.data.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressStore {

    private final AddressRepository addressRepository;

    public Address addAddress(final String street, final String hNumber, final String postCode, final String city){
        AddressEntity addressEntityToBeSave = new AddressEntity();
        addressEntityToBeSave.setStreet(street);
        addressEntityToBeSave.setHNumber(hNumber);
        addressEntityToBeSave.setPostCode(postCode);
        addressEntityToBeSave.setCity(city);
        return addressRepository.save(addressEntityToBeSave).entityToModel();
    }

//    public Address getAddress(final Long addressId) {
//        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(addressId);
//        return addressEntityOptional
//                .map(AddressEntity::entityToModel)
//                .orElseThrow(() -> new NoSuchElementException("Address not found"));
//    }

}
