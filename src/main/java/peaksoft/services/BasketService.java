package peaksoft.services;

import peaksoft.dto.simple.SimpleResponse;

public interface BasketService {
    SimpleResponse saveProductToBasketFromUser(Long productId);
    SimpleResponse deleteProductFromBasket(Long productId);

}
