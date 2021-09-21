package dz.itinfinity.orderMS.Services.EntityServices;

import dz.itinfinity.orderMS.Entities.TablesEnt.Buyer;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.BuyerNotFoundException;
import dz.itinfinity.orderMS.IDComposites.BuyerID;
import dz.itinfinity.orderMS.Repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;


//////////////// CRUD ////////////////////////////////////////////////////////////////////////////////////////////////
@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private Example<Buyer> exampleBuyer;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    /// ---------- Read --------------

    public List<Buyer> findAllBuyers(){
        return buyerRepository.findAll();
    }

    public Buyer findBuyerByIDComposite(BuyerID id){
        return buyerRepository.findById(id).orElse(null);

                //orElseThrow(() -> new BuyerNotFoundException("Buyer by firstNameBuyer: "+id.getFirstNameB()+" and lastNameBuyer: "+id.getLastNameB()+" and birthDayBuyer:"+id.getBirthDayB()+" was not found"));
    }

    public Buyer findBuyerByID(int id){
        return buyerRepository.findByIdBuyer(id);
    }

    public int findBuyerByIdGetId(Buyer buyer){
        return buyerRepository.findIdBuyer(buyer.getFirstNameB(),buyer.getLastNameB(),buyer.getBirthDayB());
    }

    /// ---------- Add --------------

    public Buyer addBuyer(Buyer buyer){
//        BuyerID idB = new BuyerID(buyer.getFirstNameB(),buyer.getLastNameB(),buyer.getBirthDayB());
        buyer = buyerRepository.saveAndFlush(buyer);
//        System.out.println(buyer.getIdBuyer());
//        System.out.println(buyer.getFirstNameB());
//        System.out.println(buyer.getLastNameB());
//        System.out.println(buyer.getBirthDayB());
//
//        Buyer b = findBuyerByIDComposite(idB);
//        System.out.println(b.getIdBuyer());
//        System.out.println(b.getFirstNameB());

        System.out.println();
        return buyer;
    }

    /// ---------- Delete --------------

    public void deleteBuyer(Buyer buyer){
        buyerRepository.delete(buyer);
    }

    public void deleteBuyerByIdBuyer(int idBuyer){
        buyerRepository.deleteByIdBuyer(idBuyer);
    }

    public void deleteBuyerByID(BuyerID id){
        buyerRepository.deleteById(id);
    }

    /// ---------- Update --------------

    public Buyer updateBuyer(Buyer buyer){
     //   buyerRepository.deleteById(new BuyerID(buyer.getFirstNameB(),buyer.getLastNameB(),buyer.getBirthDayB()));
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyerByID(Buyer buyer, int idBuyer){

      buyerRepository.delete(findBuyerByID(idBuyer));
        buyer.setIdBuyer(idBuyer);

        return buyerRepository.saveAndFlush(buyer);
    }

    ////////////////////////////////// Others Services ///////////////////////////////////////

    /// ----------- find one buyer by information --------------------------

    public Buyer findOneBuyerByInformation(Buyer buyer) {

        exampleBuyer = Example.of(buyer, ExampleMatcher.matchingAny());
        return buyerRepository.findOne(exampleBuyer).orElseThrow(() -> new BuyerNotFoundException("The buyer '" + buyer.getFirstNameB()+" "+buyer.getLastNameB() + "' is not found in the system"));
    }

    /// ----------- test existing buyer by information --------------------------

    public Boolean existBuyerByInformation(Buyer buyer) {

        exampleBuyer = Example.of(buyer, ExampleMatcher.matchingAny());
        return buyerRepository.exists(exampleBuyer);
    }
//
//    public Buyer getOrderFromBuyerRequest(BuyerRequest buyerRequest){
//
//        Buyer buyer = new Buyer();
//
//        buyer.setFirstNameB(buyerRequest.getFirstNameB());
//        buyer.setLastNameB(buyerRequest.getLastNameB());
//        buyer.setBirthDayB(buyerRequest.getBirthDayB());
//        buyer.setEmailBuyer(buyerRequest.getEmailBuyer());
//        buyer.setPhoneBuyer(buyerRequest.getPhoneBuyer());
//        buyer.setBuyerStatus((BuyerStatus) HelperService.getEnumItemChoice(BuyerStatus.class, buyerRequest.getBuyerStatus()));
//        buyer.setRegisterCommercialNumberB(buyerRequest.getRegisterCommercialNumberB());
//        buyer.setTaxIdentificationNumberB(buyerRequest.getTaxIdentificationNumberB());
//
//        return buyer;
//    }

}
