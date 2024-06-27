package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;

@RestController
@RequestMapping("profile")
@PreAuthorize("hasRole('ROLE_USER")
@CrossOrigin

public class ProfileController {

    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public ProfileController(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @GetMapping("")
    public Profile getByUserId(@PathVariable int userId) {
        Profile profile = new Profile();

        return profile;
    }

    @PutMapping("{userId}")
    public void updateProfile(@PathVariable int userId, @RequestBody Profile profile) {

    }



}
