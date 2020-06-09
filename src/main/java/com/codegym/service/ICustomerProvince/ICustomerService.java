package com.codegym.service.ICustomerProvince;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.IService;
import org.hibernate.usertype.CompositeUserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IService<Customer> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFirstName(String firstName, Pageable pageable);

    Iterable<Customer> findAllByProvince(Province province);
}
