package top.hapleow.customersupportserviceapp.model;

import lombok.Data;
import top.hapleow.customersupportserviceapp.common.Command;

@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
