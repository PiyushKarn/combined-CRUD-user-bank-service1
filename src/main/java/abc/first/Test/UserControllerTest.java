package abc.first.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import abc.first.Controllers.BankController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import abc.first.Controllers.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import abc.first.Domain.UserDomain;
import abc.first.Services.UserService;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc

//@RunWith(SpringRunner.class)
public class UserControllerTest {

    @MockBean
    private UserService userServices;

   ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    private UserDomain userDomain;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    public void setup(){
        userDomain = new UserDomain(123l,"Piyush",7540056206l,"Bangalore","Additional Details Test");
    }

    @Test
    public void testFindAllUsers() throws Exception {
        UserDomain userDomain = new UserDomain(123l, "Piyush", 7540056206l, "Bangalore", "additional Details Test");
        List<UserDomain> users = Arrays.asList(userDomain);
        when(userServices.findAll()).thenReturn(users);
        mockMvc.perform(get("http://localhost:9090/users/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfGetUsersListGettingGenerated() throws Exception{
        when(userServices.findAll()).thenReturn(Collections.singletonList(userDomain));
        mockMvc.perform(get("http://localhost:9090/users/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userServices.saveUser(userDomain)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        Assertions.assertNotNull(userDomain);
        Assertions.assertEquals(123, userDomain.getId());
    }

    @Test
    public void testGetUserById_IsUserIdMatching() throws Exception {
        when(userServices.findById(123L)).thenReturn(Optional.ofNullable(userDomain));
        mockMvc.perform(get("http://localhost:9090/users/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)));
    }

    @Test
    public void testIfUserPhoneNumberNotEmpty() throws Exception {
        when(userServices.findById(123L)).thenReturn(Optional.ofNullable(userDomain));
        mockMvc.perform(get("http://localhost:9090/users/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.phoneNumber").isNotEmpty());
    }

    @Test
    public void testIfUserIsGettingDeleted() throws Exception {
        when(userServices.deleteUser(userDomain)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        mockMvc.perform(delete("http://localhost:9090/users/")
                .content(objectMapper.writeValueAsString(userDomain.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
   }

}


