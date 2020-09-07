create table user
(
    user_id            bigint auto_increment comment '用户ID'
        primary key,
    user_name          varchar(200)              default ''                null comment '用户名称',
    user_seq_id        varchar(64)               default ''                null comment '用户唯一识别号',
    user_nick_name     varchar(30)               default ''                null comment '用户昵称',
    user_password      varchar(64)               default ''                null comment '用户密码',
    user_password_salt varchar(6)                default ''                null comment '用户密码加盐',
    user_telephone     varchar(18)               default ''                null comment '用户手机号',
    user_idcard_no     varchar(18)               default ''                null comment '用户身份证号',
    user_remark        varchar(200) charset utf8 default ''                null comment '用户备注',
    user_create_time   datetime                  default CURRENT_TIMESTAMP null comment '创建时间',
    user_update_time   datetime                  default CURRENT_TIMESTAMP null comment '修改时间'
)
    comment '用户表';
