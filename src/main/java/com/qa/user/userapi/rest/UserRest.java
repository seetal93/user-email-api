package com.qa.user.userapi.rest;

import com.qa.user.userapi.persistence.domain.User;
import com.qa.user.userapi.persistence.domain.Email;
import com.qa.user.userapi.persistence.domain.SentUser;
import com.qa.user.userapi.persistence.domain.SentEmail;
import com.qa.user.userapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RequestMapping("${path.base}")
@RestController
public class UserRest {

    @Autowired
    private UserService service;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Value("${url.user}")
    private String userURL;
    
    @Value("${path.genUserName}")
    private String userNameGeneratorPath;
    
    @Value("${url.prize}")
    private String emailURL;
    

    @GetMapping("${path.getUsers}")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("${path.getUserById}")
    public User getUser(@PathVariable Long userId) {
        return service.getUser(userId);
    }

    @DeleteMapping("${path.deleteUser}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @PutMapping("${path.updateUser}")
    public ResponseEntity<Object> updateAccount(@RequestBody User account, @PathVariable Long id) {
        return service.updateUser(account, id);
    }
    
    @PostMapping("${path.createUser}")
    public User createUser(@RequestBody User user) {
        user = setUserNameAndEmail(user);
        sendToQueue(user);
    	return service.addUser(user);
    }

    private User setUserNameAndEmail(User user){
        String generatedUserName = restTemplate.getForObject(userURL + userNameGeneratorPath, String.class);
        Email email = restTemplate.getForObject(emailURL + generatedUserName, Email.class);

        user.setUserName(generatedUserName);
        user.setEmail(email);
        return user;
    }

    private void sendToQueue(User account){
        SentUser accountToStore =  new SentUser(account);
        jmsTemplate.convertAndSend("AccountQueue", accountToStore);
    }

}
