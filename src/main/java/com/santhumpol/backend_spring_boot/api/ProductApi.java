package com.santhumpol.backend_spring_boot.api;

import com.santhumpol.backend_spring_boot.business.ProductBusiness;
import com.santhumpol.backend_spring_boot.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductApi {
    private final ProductBusiness business;

    public ProductApi(ProductBusiness business) {
        this.business = business;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException {
        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }
}
