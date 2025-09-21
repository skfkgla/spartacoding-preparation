package com.sparta.myselectshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.naver.dto.ItemDto;
import com.sparta.myselectshop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public static final int MIN_MY_PRICE = 100;

	@Transactional
	public ProductResponseDto createProduct(ProductRequestDto requestDto) {
		Product product = productRepository.save(new Product(requestDto));
		return new ProductResponseDto(product);
	}

	@Transactional
	public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
		int myprice = requestDto.getMyprice();
		if (myprice < MIN_MY_PRICE) {
			throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 입력해주세요");
		}
		Product product = productRepository.findById(id).orElseThrow(() ->
			new NullPointerException("상품을 찾을 수 없습니다."));

		product.update(requestDto);

		return new ProductResponseDto(product);
	}

	public List<ProductResponseDto> getProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductResponseDto> responseDtos = new ArrayList<>();

		for (Product product : productList) {
			responseDtos.add(new ProductResponseDto(product));
		}

		return responseDtos;
	}

	@Transactional
	public void updateBySearch(Long id, ItemDto itemDto) {
		Product product = productRepository.findById(id).orElseThrow(()
			-> new NullPointerException("상품이 존재하지 않습니다."));
		product.updateByItemDto(itemDto);
	}
}
