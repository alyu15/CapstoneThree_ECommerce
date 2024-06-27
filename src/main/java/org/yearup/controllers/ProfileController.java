package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")

public class ProfileController {

    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public ProfileController(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @GetMapping("{userId}")
    public Profile getByUserId(Principal principal) {

        try {

            int userId = getUserId(principal);
            Profile profile = profileDao.getByUserId(userId);

            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return profile;

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    @PutMapping("{userId}")
    public void updateProfile(Principal principal, @RequestBody Profile profile) {

        try {

            int userId = getUserId(principal);
            profile = profileDao.getByUserId(userId);

            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            profileDao.updateProfile(userId, profile);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    public int getUserId(Principal principal) {

        String userName = principal.getName();
        // find database user by userId
        User user = userDao.getByUserName(userName);

        return user.getId();

    }

}
