package com.codingdojo.authentication.Services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	// Validate User for - duplicate email and password mismatch
	public void validate(User newUser, Errors errors) {
		// check password match
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			errors.rejectValue("password", "mismatch", "Password does not match!");
		}
		// check duplicate email
		if (userRepository.findByEmail(newUser.getEmail()) != null) {
			errors.rejectValue("email", "unique", "Email already has registered");
		}
	} 
    
    // Hash users password and add to database
    public User registerUser(User newUser) {
        String hasedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hasedPassword);
        userRepository.save(newUser);
        
        return null;
    }
    
    // Find a user by email
    public User findUserByEmai(String email) {
    	return userRepository.findByEmail(email);
    }
    
    // Find a user by id
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }

    // Authenticate a user
    public boolean authenticateUser(LoginUser newLogin, Errors errors) {
    	// Find the user by email
    	User user = userRepository.findByEmail(newLogin.getEmail());
    	if (user == null) {
    		errors.rejectValue("email", "incorrectEmail", "Email not found");
    		
    		return false;
    	} else {
    		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		    errors.rejectValue("password", "incorrectPassword	", "Password does not match!");
    		    
    		    return false;
    		}
    		
    		// if user found, check if password match, return true
    		return true;
    	}
    }
    
    // update a user
    
    // delete a user   
}
