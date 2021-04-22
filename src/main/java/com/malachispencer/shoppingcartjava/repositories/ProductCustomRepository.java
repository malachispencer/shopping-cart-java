package com.malachispencer.shoppingcartjava.repositories;

import java.util.Map;

public interface ProductCustomRepository {
    void updateStock(Map itemData);
}
