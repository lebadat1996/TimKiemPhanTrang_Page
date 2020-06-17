package com.codegym.service.ICustomerProvince;

import com.codegym.model.AppUser;

public interface IAppUserService {
    AppUser getUserByUserName(String username);
}
