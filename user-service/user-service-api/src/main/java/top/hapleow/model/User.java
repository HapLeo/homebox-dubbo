package top.hapleow.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    @TableId
    private Long userId;
    private String userNickName;
    private String userName;
    private String userSeqId;
    private String userPassword;
    private String userPasswordSalt;
    private String userTelephone;
    private String userIdcardNo;
    private LocalDateTime userCreateTime;
    private LocalDateTime userUpdateTime;

}
