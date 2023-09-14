package peaksoft.services.servicesImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Basket;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.BasketRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.services.BasketService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    @Override
    @Transactional
    public SimpleResponse saveProductToBasketFromUser(Long productId) {
        String message = "Success add product to basket";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with id:" + email + " not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product with id:"+productId+" not found"));


        if (user.getBasket() == null) {
            Basket basket = new Basket();
            basket.setUser(user);
            user.setBasket(basket);
            basketRepository.save(basket);
        }

        if (user.getBasket().getProducts() != null) {
            for (Product prod : user.getBasket().getProducts()) {
                if (prod.getId().equals(product.getId())) {

                    product.getBaskets().remove(user.getBasket());

                    user.getBasket().getProducts().remove(product);

                    message = "Successfully deleted product in basket";
                } else user.getBasket().getProducts().add(product);
            }
        }
        else {
            user.getBasket().setProducts(Collections.singletonList(product));
            product.add(user.getBasket());
        }

//
//        List<Basket> baskets = basketRepository.findAll();
//        for(Basket basket1 : baskets) {
//            if (basket1.getUser().equals(user) && (basket1.getProducts().equals(product))) {
//                basketRepository.deleteById(basket1.getId());
//                return SimpleResponse.builder()
//                        .httpStatus(HttpStatus.OK)
//                        .message("Chosen is deleted...")
//                        .build();
//            }
//        }
//        Basket basket2 = new Basket();
//        basket2.setUser(user);
//        basket2.getProducts().add(product);
//        product.getBaskets().size();
//        basketRepository.save(basket2);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .build();
    }


}
