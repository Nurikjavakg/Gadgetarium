package peaksoft.services.servicesImpl;

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

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private BasketRepository basketRepository;

    @Override
    public SimpleResponse saveProductToBasketFromUser(Long productId, Long basketId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with id:" + email + " not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product with id:"+productId+" not found"));


        if(!user.getBasket().equals(user)){
            throw new NotFoundException("You have permission to delete this comment");
        }

        Basket basket = new Basket();
        if(basket.getUser().equals(user)){
            basketRepository.deleteById(basket.getId());
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Chosen is deleted...")
                    .build();
        }

        basket.setUser(user);
        basket.getProducts().add(product);
        basketRepository.save(basket);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Chosen is deleted...")
                .build();
    }


}
