package com.carrentalcompany.service;

import com.carrentalcompany.model.User;
import com.carrentalcompany.response.Result;

public interface UserService {
    Result register(User user, String password2);
}
