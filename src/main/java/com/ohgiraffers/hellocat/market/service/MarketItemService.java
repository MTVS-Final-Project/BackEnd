package com.ohgiraffers.hellocat.market.service;

import com.ohgiraffers.hellocat.market.dto.MarketItemRequestDto;
import com.ohgiraffers.hellocat.market.dto.MarketItemResponseDto;
import com.ohgiraffers.hellocat.market.entity.MarketItem;
import com.ohgiraffers.hellocat.market.repository.MarketItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MarketItemService {

    private final MarketItemRepository marketItemRepository;

    @Transactional(readOnly = true)
    public List<MarketItemResponseDto> findMarketItemList() {

        return marketItemRepository.findAll()
                .stream()
                .map(MarketItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public MarketItemResponseDto createMarketItem(MarketItemRequestDto requestDto) {

        MarketItem marketItem = new MarketItem(requestDto);

        MarketItem savedItem = marketItemRepository.save(marketItem);

        return new MarketItemResponseDto(savedItem);
    }

    public MarketItemResponseDto updateMarketItem(Long itemId, MarketItemRequestDto requestDto) {

        MarketItem foundItem = marketItemRepository.findById(itemId)
                .orElseThrow(() -> {
                    log.error("아이템을 찾을 수 없습니다. itemId={}", itemId);
                    return new IllegalArgumentException("아이템을 찾을 수 없습니다.");
                });

        MarketItem updatedItem = foundItem.update(requestDto);

        return new MarketItemResponseDto(updatedItem);
    }

    public void deleteMarketItem(Long itemId) {

        MarketItem foundItem = marketItemRepository.findById(itemId)
                .orElseThrow(() -> {
                    log.error("아이템을 찾을 수 없습니다. itemId={}", itemId);
                    return new IllegalArgumentException("아이템을 찾을 수 없습니다.");
                });

        marketItemRepository.delete(foundItem);
    }
}
