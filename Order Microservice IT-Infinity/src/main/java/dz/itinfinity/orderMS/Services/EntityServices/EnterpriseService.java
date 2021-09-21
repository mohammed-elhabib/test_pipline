package dz.itinfinity.orderMS.Services.EntityServices;

import dz.itinfinity.orderMS.Entities.TablesEnt.Enterprise;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.EnterpriseNotFoundException;
import dz.itinfinity.orderMS.IDComposites.EnterpriseID;
import dz.itinfinity.orderMS.Repositories.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

//////////////// CRUD ////////////////////////////////////////////////////////////////////////////////////////////////
@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private Example<Enterprise> exampleEnterprise;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    /// ---------- Read --------------

    public List<Enterprise> findAllEnterprises() {
        return enterpriseRepository.findAll();
    }

    public Enterprise findEnterpriseByIDComposite(EnterpriseID id) {
        return enterpriseRepository.findById(id).
                orElse ( null);
    }

    public Enterprise findEnterpriseByID(int id) {
        return enterpriseRepository.findByIdEnterprise(id);
    }

    /// ---------- Add --------------

    public Enterprise addEnterprise(Enterprise enterprise) {
        enterprise = enterpriseRepository.saveAndFlush(enterprise);
        return findEnterpriseByIDComposite(new EnterpriseID(enterprise.getEnterpriseName(),enterprise.getTown()));
    }

    /// ---------- Delete --------------

    public void deleteEnterpriseByIdEnterprise(int idEnterprise) {
        enterpriseRepository.deleteByIdEnterprise(idEnterprise);
    }

    public void deleteEnterprise(Enterprise enterprise) {
        enterpriseRepository.delete(enterprise);
    }

    public void deleteEnterpriseByID(EnterpriseID id) {
        enterpriseRepository.deleteById(id);
    }

    /// ---------- Update --------------

    public Enterprise updateEnterprise(Enterprise enterprise) {
        enterpriseRepository.deleteById(new EnterpriseID(enterprise.getEnterpriseName(),enterprise.getTown()));
        return enterpriseRepository.save(enterprise);
    }

    /// ---------- Get Enterprise by his information -------------------------

    public Enterprise findOneEnterpriseByInformation(Enterprise enterprise) {

        exampleEnterprise = Example.of(enterprise, ExampleMatcher.matchingAll());

        return enterprise;
        //return enterpriseRepository.findOne(exampleEnterprise).orElseThrow(() -> new EnterpriseNotFoundException("The '" + enterprise.getEnterpriseName() + "' enterprise is not found in the system"));
    }

    /// ----------- test existing enterprise by information --------------------------

    public Boolean existEnterpriseByInformation(Enterprise enterprise) {
        exampleEnterprise = Example.of(enterprise, ExampleMatcher.matchingAll());
        return enterpriseRepository.exists(exampleEnterprise);

    }
}