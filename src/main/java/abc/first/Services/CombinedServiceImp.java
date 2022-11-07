package abc.first.Services;

import abc.first.Domain.BankDomain;
import abc.first.Domain.CombinedObject;
import abc.first.Domain.UserDomain;
import abc.first.Repositories.BankRepository;
import abc.first.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedServiceImp implements CombinedService{
    @Autowired
    BankRepository bankRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public String save(CombinedObject combinedObject) {

        try {
            UserDomain userDomain = new UserDomain(
                    -1L,
                    combinedObject.getName(),
                    combinedObject.getPhoneNumber(),
                    combinedObject.getAddress(),
                    combinedObject.getAdditionalDetails()
            );
            userDomain = userRepository.save(userDomain);

            BankDomain bankDomain = new BankDomain(
                    -1L,
                    userDomain.getId(),
                    combinedObject.getBankName(),
                    combinedObject.getAccountNumber(),
                    combinedObject.getIfscCode(),
                    combinedObject.getAdditionalDetails()
            );
            bankRepository.save(bankDomain);

        }
        catch(Exception e){
            return "Error! Duplicate entry!";
        }
        return "User and Bank details added successfully";
    }

    @Override
    public CombinedObject getCombinedDomain(Long phoneNumber) {

        try {
            UserDomain userDomain = userRepository.findByPhoneNumber(phoneNumber).get(0);
            BankDomain bankDomain = bankRepository.findByUserId(userDomain.getId()).get(0);

            return new CombinedObject(userDomain, bankDomain);

        } catch (Exception e){
            return null;
        }

    }

    @Override
    public String delete(Long id){

        try {
            bankRepository.deleteByUserId(id);
            userRepository.deleteById(id);
        }
        catch (Exception e){
            return "User Id doesn't exist!";
        }
        return "User deleted Successfully";
    }

    @Override
    public String update(CombinedObject combinedObject) {
        try {
            UserDomain userDomain = new UserDomain(combinedObject.getUserId(),
                    combinedObject.getName(),
                    combinedObject.getPhoneNumber(),
                    combinedObject.getAddress(),
                    combinedObject.getAdditionalDetails()
            );
            userDomain = userRepository.save(userDomain);

            BankDomain bankDomain = new BankDomain(combinedObject.getBankId(),
                    userDomain.getId(),
                    combinedObject.getBankName(),
                    combinedObject.getAccountNumber(),
                    combinedObject.getIfscCode(),
                    combinedObject.getAdditionalDetails()
            );
            bankRepository.save(bankDomain);

        } catch (Exception e) {
            return "Error! Some mandatory field is missing. Please Check all the fields";
        }
        return "User updated!";
    }

}
