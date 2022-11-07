package abc.first.Services;

import abc.first.Domain.UserDomain;
import abc.first.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDomain> findAll() {
        List<UserDomain> allUsers = userRepository.findAll();
        //System.out.println(allUsers);
        return allUsers;
    }
    @Override
    public ResponseEntity<String> saveUser(UserDomain user) {

        try {
            userRepository.save(user);
            return new ResponseEntity<String>("User Added Successfully", HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e) {
            System.out.println("Error!");
        }
        return new ResponseEntity<String>("Duplicate entry exists!!", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> updateUser(UserDomain newUserData) {

        UserDomain existingUser
                = userRepository.findById(newUserData.getId())
                .orElse(null);
        if (existingUser == null) {
            return new ResponseEntity<String>("No such user exists!!", HttpStatus.OK);
        }
        else {
            userRepository.save(newUserData);
            return new ResponseEntity<String>("User data updated!!", HttpStatus.OK);
        }

    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> deleteUser(UserDomain deleteUserData) {

        UserDomain existingUser
                = userRepository.findById(deleteUserData.getId())
                .orElse(null);
        if (existingUser != null) {
            userRepository.delete(deleteUserData);
            return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("No such user exists!", HttpStatus.OK);
        }
    }

//    @Override
//    public String deleteUser(UserDomain deleteUserData) {
//
//        UserDomain existingUser
//                = userRepository.findById(deleteUserData.getId())
//                .orElse(null);
//        if (existingUser != null) {
//            userRepository.delete(deleteUserData);
//            return "Data Deleted";
//
//        }
//        else {
//            return "No such user exists!!";
//        }
//    }

    @Override
    public Page<UserDomain> findUsersWithPagination(int offset,int limit){

        try {
            Page<UserDomain> users = userRepository.findAll(PageRequest.of(offset, limit));
            return users;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Please enter valid offset and limit");

        }
            Page<UserDomain> users = userRepository.findAll(PageRequest.of(0, 5));
        System.out.println("Passing default values to limit and offset");
        return users;
    }

    @Override
    public Iterable<UserDomain> list() {
        return userRepository.findAll();
    }
    @Override
    public void save(List<UserDomain> users){
        userRepository.saveAll(users);
    }

}
