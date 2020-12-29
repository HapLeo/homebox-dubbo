package top.hapleow.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Order implements Serializable {

    private Long orderId;
    private String orderNo;
    private Long orderUserId;
    private String orderUserNickName;
    private Double orderProductSumPrice;
    private LocalDateTime orderCreateTime;
    private LocalDateTime orderUpdateTime;
}
