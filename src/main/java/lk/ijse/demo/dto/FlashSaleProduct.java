package lk.ijse.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlashSaleProduct {
    private Long productId;
    private String imageUrl;
    private String title;
    private int price;
    private int priceDecimal;
    private double originalPrice;
    private int discount;
}
