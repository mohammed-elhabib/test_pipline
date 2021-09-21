package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.repository.AddressRepository;
import com.example.delivery_microservice.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }
    public AddressEntity findByIdAddress(int idAddress) {

        return addressRepository.findByIdAddress(idAddress);
    }

    public int saveAddress(AddressEntity address) throws DataAccessException {

       try {
           AddressEntity addrs=addressRepository.save(address);

           int idAddress=addressRepository.findIdAddress(addrs.getIdCommune(),addrs.getCity(),addrs.getFloor(),addrs.getStreet(),addrs.getHomeNumber());
           System.out.println(idAddress);
          return idAddress;



       }catch (DataAccessException exp){
           System.out.println(exp.toString());


           return  -1;
       }

    }

}
