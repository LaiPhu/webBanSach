package com.example.webSpring.api.repository.user;

import com.example.webSpring.api.model.user.OrderDetail;

import java.util.List;

public interface OrderDetailRepositoryCustom {
    List<OrderDetail> findTop10BooksByNumber(Integer num);
}
