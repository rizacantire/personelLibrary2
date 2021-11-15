package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.ShopListService;
import com.rza.BookSelf.dataAccess.ShopListDao;
import com.rza.BookSelf.entities.concretes.ShopList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopListManager implements ShopListService {
    @Autowired
    private ShopListDao shopListDao;

    public ShopListManager(ShopListDao shopListDao) {
        this.shopListDao = shopListDao;
    }

    @Override
    public List<ShopList> getAlll() {
        return this.shopListDao.findAll();
    }

    @Override
    public boolean add(ShopList entity) {
        this.shopListDao.save(entity);
        return true;
    }

    @Override
    public boolean update(ShopList entity) {
        this.shopListDao.save(entity);
        return true;
    }

    @Override
    public void delete(ShopList entity) {
        this.shopListDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {
        this.shopListDao.deleteById(id);
        return true;
    }
}
