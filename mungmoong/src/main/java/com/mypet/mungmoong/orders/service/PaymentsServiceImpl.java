package com.mypet.mungmoong.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.mapper.PaymentsMapper;
import com.mypet.mungmoong.orders.model.Payments;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsMapper paymentsMapper;

    @Override
    public List<Payments> list() throws Exception {
        return paymentsMapper.list();
    }

    @Override
    public Payments select(String id) throws Exception {
        return paymentsMapper.select(id);
    }

    @Override
    public int insert(Payments payments) throws Exception {
        Payments oldPayments = selectByOrdersId(payments.getOrdersId());
        if( oldPayments == null ) {
            return paymentsMapper.insert(payments);
        }
        return 0;
    }

    @Override
    public int update(Payments payments) throws Exception {
        return paymentsMapper.update(payments);
    }

    @Override
    public int delete(String id) throws Exception {
        return paymentsMapper.delete(id);
    }

    @Override
    public Payments selectByOrdersId(String ordersId) {
        return paymentsMapper.selectByOrdersId(ordersId);
    }

    @Override
    public int merge(Payments payments) throws Exception {
        if( payments == null || select(payments.getId()) == null ) 
            return insert(payments);

        return update(payments);
    }
}