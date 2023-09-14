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
                .orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found"));


        if (user.getBasket() == null) {
            Basket basket = new Basket();
            basket.setUser(user);
            user.setBasket(basket);
            basketRepository.save(basket);
        }

        for (Product p :user.getBasket().getProducts()) {
            if (p.equals(product)){
                user.getBasket().getProducts().remove(p);
                for (Basket b:product.getBaskets()) {
                    if (b.getUser().equals(user)){
                        b.getProducts().remove(product);
                    }
                }
                return SimpleResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Product was deleted from user's basket")
                        .build();
            }
        }
        user.getBasket().getProducts().add(product);
        for (Basket b :product.getBaskets()) {
            if (!b.getUser().equals(user)){
                product.add(user.getBasket());
            }
        }
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(message)
                    .build();
        }


    }
