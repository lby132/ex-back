package com.example.back.exback.api.service.order;

import com.example.back.exback.api.controller.order.request.OrderRequest;
import com.example.back.exback.api.controller.order.response.OrderResponse;
import com.example.back.exback.api.exception.NotEnoughStockException;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemRepository;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.order.Order;
import com.example.back.exback.domain.order.OrderRepository;
import com.example.back.exback.domain.stock.Stock;
import com.example.back.exback.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(PostNotFound::new);

        List<String> itemNames = request.getItemName();
        List<Item> items = findItemsBy(itemNames);

        deductStockQuantities(items);

        Order order = Order.createOrder(member, items);
        return OrderResponse.of(orderRepository.save(order));
    }

    private void deductStockQuantities(List<Item> items) {
        List<String> stockItemNames = extractStock(items);

        Map<String, Stock> stockMap = createStockMapBy(stockItemNames);

        Map<String, Long> countingMap = createCountingMapBy(stockItemNames);

        for (String stockItemName : new HashSet<>(stockItemNames)) {
            Stock stock = stockMap.get(stockItemName);
            int quantity = countingMap.get(stockItemName).intValue();

            if (stock.isQuantityLessThan(quantity)) {
                throw new NotEnoughStockException("재고가 부족합니다.");
            }

            stock.deductQuantity(quantity);
        }
    }

    private Map<String, Long> createCountingMapBy(List<String> stockItemNames) {
        return stockItemNames.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    }

    private Map<String, Stock> createStockMapBy(List<String> stockItemNames) {
        List<Stock> stock = stockRepository.findAllByItemNameIn(stockItemNames);
        return stock.stream()
                .collect(Collectors.toMap(Stock::getItemName, s -> s));
    }

    private List<String> extractStock(List<Item> items) {
        return items.stream()
                .map(Item::getItemName)
                .collect(Collectors.toList());
    }

    private List<Item> findItemsBy(List<String> itemNames) {
        List<Item> items = itemRepository.findAllByItemNameIn(itemNames);
        Map<String, Item> itemMap = items.stream()
                .collect(Collectors.toMap(Item::getItemName, i -> i));

        return itemNames.stream()
                .map(itemMap::get)
                .collect(Collectors.toList());
    }
}
