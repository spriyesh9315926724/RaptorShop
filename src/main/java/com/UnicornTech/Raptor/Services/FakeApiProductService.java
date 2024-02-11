package com.UnicornTech.Raptor.Services;

import com.UnicornTech.Raptor.DTO.FakeApiDto;
import com.UnicornTech.Raptor.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreApiImpl")
public class FakeApiProductService implements ProductInterface<FakeApiDto>{

    private final RestTemplateBuilder rtBuilder;
    private static final String exturl = "https://fakestoreapi.com/products";
    public FakeApiProductService(RestTemplateBuilder builder){
        this.rtBuilder = builder;
    }
    @Override
    public ResponseEntity<List<FakeApiDto>> getProducts() {
        RestTemplate rt = rtBuilder.build();
        List<FakeApiDto>productList = new ArrayList<>();
        ResponseEntity<FakeApiDto[]>response =  rt.getForEntity(exturl,FakeApiDto[].class);
        for(FakeApiDto dto:response.getBody()){
            productList.add(dto);
        }
        return ResponseEntity.accepted().body(productList);
    }

    @Override
    public ResponseEntity<List<FakeApiDto>> getLimitedResult(Integer limit) {
        RestTemplate rt = rtBuilder.build();
        List<FakeApiDto>productList = new ArrayList<>();
        ResponseEntity<FakeApiDto[]>response =  rt.getForEntity(exturl+"?limit="+limit.toString(),FakeApiDto[].class);
        for(FakeApiDto dto:response.getBody()){
            productList.add(dto);
        }
        return ResponseEntity.accepted().body(productList);
    }

    @Override
    public ResponseEntity<List<FakeApiDto>> getSortedResult(String sortType) {
        RestTemplate rt = rtBuilder.build();
        List<FakeApiDto>productList = new ArrayList<>();
        ResponseEntity<FakeApiDto[]>response =  rt.getForEntity(exturl+"?sort="+sortType,FakeApiDto[].class);
        for(FakeApiDto dto:response.getBody()){
            productList.add(dto);
        }
        return ResponseEntity.accepted().body(productList);
    }


    @Override
    public FakeApiDto createProduct(FakeApiDto dto) {
        RestTemplate rt = rtBuilder.build();
        return rt.postForEntity(exturl,dto,FakeApiDto.class).getBody();
    }

    @Override
    public Product updateProduct(Integer productId , FakeApiDto dto) {
        RestTemplate rt = rtBuilder.build();
        RequestCallback requestCallback = rt.acceptHeaderRequestCallback(FakeApiDto.class);
        ResponseExtractor<ResponseEntity<FakeApiDto>> responseExtractor = rt.responseEntityExtractor(FakeApiDto.class);
        ResponseEntity<FakeApiDto> response = rt.execute(exturl+"/"+productId.toString(), HttpMethod.PUT, requestCallback, responseExtractor, productId);
        if(response!=null)return response.getBody();
        return null;
    }

    @Override
    public Product deleteProduct(Integer productId){
        RestTemplate rt = rtBuilder.build();
        RequestCallback requestCallback = rt.acceptHeaderRequestCallback(FakeApiDto.class);
        ResponseExtractor<ResponseEntity<FakeApiDto>> responseExtractor = rt.responseEntityExtractor(FakeApiDto.class);
        ResponseEntity<FakeApiDto> response = rt.execute(exturl+"/"+productId.toString(), HttpMethod.DELETE, requestCallback, responseExtractor, productId);
        return response.getBody();
    }

    public FakeApiDto getProductById(Integer productId) {
        RestTemplate rt = rtBuilder.build();
        ResponseEntity<FakeApiDto> dto = rt.getForEntity(exturl+"/"+productId.toString(),FakeApiDto.class);
        return dto.getBody();
    }
}
