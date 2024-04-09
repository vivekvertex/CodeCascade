package com.ums.service;

import com.ums.dto.logindto;
import com.ums.dto.userdto;
import com.ums.entity.Appuser;
import com.ums.repository.AppuserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userserviceimpl implements userservice{

    private JWTservice jwtService;



    private AppuserRepository appuserRepository;

    public userserviceimpl(JWTservice jwtService, AppuserRepository appuserRepository) {
        this.jwtService = jwtService;
        this.appuserRepository = appuserRepository;
    }

    @Override
    public userdto saveUser(userdto user) {
        Appuser appuser = maptoentity(user);
        Appuser saved = appuserRepository.save(appuser);


        userdto maptodto = maptodto(saved);
        return maptodto;



    }

    @Override
    public String verifyLogin(logindto login) {
        Optional<Appuser> byUsername = appuserRepository.findByUsername(login.getUsername());
        if (byUsername.isPresent()) {
            Appuser appuser = byUsername.get();
            if (BCrypt.checkpw(login.getPassword(), appuser.getPassword())) {
               return jwtService.generateToken(appuser);


            };

        }
        return null;
    }

    public Appuser maptoentity(userdto user) {
        Appuser a1 = new Appuser();
        a1.setUsername(user.getUsername());
        a1.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(1)));

        a1.setEmailId(user.getEmailId());
        a1.setName(user.getName());
        return a1;


    }
    public userdto maptodto(Appuser appuser){

        userdto user = new userdto();
        user.setUsername(appuser.getUsername());
        user.setPassword(appuser.getPassword());
        user.setEmailId(appuser.getEmailId());
        user.setName(appuser.getName());
        user.setId(appuser.getId());
        return user;

    }
}
