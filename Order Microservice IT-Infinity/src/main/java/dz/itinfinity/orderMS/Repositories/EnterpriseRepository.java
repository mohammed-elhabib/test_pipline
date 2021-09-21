package dz.itinfinity.orderMS.Repositories;

import dz.itinfinity.orderMS.Entities.TablesEnt.Enterprise;
import dz.itinfinity.orderMS.IDComposites.EnterpriseID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, EnterpriseID> {
    public Enterprise findByIdEnterprise(int idEnterprise);
    public void deleteByIdEnterprise(int idEnterprise);
}