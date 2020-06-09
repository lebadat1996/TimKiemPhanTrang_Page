package com.codegym.service.CustomerProvince;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerProvince.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @PersistenceContext
    EntityManager en;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer model) {
        customerRepository.save(model);
    }

    @Override
    public void update(Customer model) {
        if (model.getId() != null) {
            en.merge(model);
        } else {
            en.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = customerRepository.findOne(id);
        if (customer.getId() != null) {
            en.remove(id);
        } else {
            System.out.println("loi");
        }
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFirstName(String firstName, Pageable pageable) {
        return customerRepository.findAllByFirstName(firstName, pageable);
    }


    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findALLByProvince(province);
    }
}
