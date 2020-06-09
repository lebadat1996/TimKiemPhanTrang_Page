package com.codegym.service.CustomerProvince;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepository;
import com.codegym.service.ICustomerProvince.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class ProvinceService implements IProvinceService {
    @Autowired
    IProvinceRepository provinceRepository;
    @PersistenceContext
    EntityManager en;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province getById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void update(Province province) {
        if (province.getId() != null) {
            en.merge(province);
        } else {
            en.persist(province);
        }
    }

    @Override
    public void remove(Long id) {
        Province province = provinceRepository.findOne(id);
        if (province != null) {
            en.remove(province);
        }
    }

    @Override
    public void save(Province model) {
        provinceRepository.save(model);
    }
}
