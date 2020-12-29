package top.hapleow.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Order implements Serializable {

    @TableId
    private Long orderId;
    private String orderNo;
    private Long orderUserId;
    private String orderUserNickName;
    private Double orderProductSumPrice;
    private LocalDateTime orderCreateTime;
    private LocalDateTime orderUpdateTime;
}
