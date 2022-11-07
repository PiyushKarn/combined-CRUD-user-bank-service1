package abc.first.Controllers;


import java.util.List;
import java.util.Optional;

import abc.first.Domain.BankDomain;
import abc.first.Domain.UserDomain;

import abc.first.Repositories.UserRepository;
import abc.first.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userServices;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userList")
    public Iterable<UserDomain> list() {
        return userServices.list();
    }

    @GetMapping() //getALLTickets
    public List<UserDomain> allUsers() {
        return userServices.findAll();

    }

    @PostMapping()  //AddNewUser
    public ResponseEntity<String> addUser(@RequestBody UserDomain userdata)
    {
        return userServices.saveUser(userdata);
    }

    @PutMapping()   //updateExistingUsers
    public ResponseEntity<String> updateUser(@RequestBody UserDomain newUserData)
    {
        return userServices.updateUser(newUserData);
    }

    @GetMapping("/{id}")    //findUserById
    public Optional<UserDomain> getUserById(@PathVariable Long id) {
        Optional<Optional<UserDomain>> userDomain = Optional.ofNullable(userServices.findById(id));
        if (userDomain.isPresent()) {
            return userDomain.get();
        } else {
            return null;
        }
    }

    @DeleteMapping()       //deleteUserById
    public ResponseEntity<String> deleteUser(@RequestBody UserDomain deleteUserData){
        return userServices.deleteUser(deleteUserData);
    }

//@DeleteMapping()       //deleteUserById
//public String deleteUser(@RequestBody UserDomain deleteUserData){
//    return userServices.deleteUser(deleteUserData);
//}

//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
//        boolean deleteUserById = userServices.deleteUserById(id);
//        if (deleteUserById) {
//            return new ResponseEntity<>(("User deleted - Order ID:" + id), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(("User deletion failed - Order ID:" + id), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/pagination")    //findUserByPagination
    public Page<UserDomain> getUserById(@RequestParam int offset, @RequestParam int limit) {
        return userServices.findUsersWithPagination(offset,limit);
    }

//    @PostMapping("/bulk")
//    public ResponseEntity<String> insertUsersInBulk(){
//        UserDomain user1 = new UserDomain(456l,"Aniket",7869876589l,"Bellandur","Additional info 1");
//        UserDomain user2 = new UserDomain(457l,"Binod",7854376589l,"HSR","Additional info 2");
//        UserDomain user3 = new UserDomain(458l,"Chetan",7854374449l,"Whitefield","Additional info 3");
//
//        List<UserDomain> users = Arrays.asList(user1,user2,user3);
//        userRepository.saveAll(users);
//        return (ResponseEntity<String>) ResponseEntity.created(URI.create("/bulk"));
//    }

    @GetMapping(value = "/callAccounts")
    private String getAccounts(){
        String uri = "http://localhost:9090/accounts/";
        RestTemplate restTemplate = new RestTemplate();
        String results = restTemplate.getForObject(uri, String.class);
        return results;
    }

}