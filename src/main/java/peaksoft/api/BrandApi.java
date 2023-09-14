package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.brand.BrandRequest;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.BrandService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class BrandApi {
    private final BrandService brandService;

    @PostMapping("/saveBrand")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveBrand(@RequestBody @Valid BrandRequest brandRequest) {
        brandService.saveBrand(brandRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Brand with name: %s successfully saved!", brandRequest.brandName()))
                .build();
    }

}
