package com.ums.service;

import com.ums.dto.logindto;
import com.ums.dto.userdto;

public interface userservice {

    public userdto saveUser(userdto user);

    String verifyLogin(logindto login);
}

