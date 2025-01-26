package lk.ijse.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleProduct {
    @Id
    private Long productId;
    private String imageUrl;
    private String title;
    private int price;
    private int priceDecimal;
    private double originalPrice;
    private int discount;
}