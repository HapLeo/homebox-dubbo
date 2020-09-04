create table `order`
(
    order_id                bigint auto_increment comment '订单ID'
        primary key,
    order_no                varchar(40)              default ''                null comment '订单编码',
    order_user_id           bigint                   default -1                null comment '订单用户ID',
    order_user_nick_name    varchar(30) charset utf8 default ''                null comment '订单用户昵称',
    order_product_sum_price decimal(18, 2)           default 0.00              null comment '商品总额',
    order_create_time       datetime                 default CURRENT_TIMESTAMP null comment '订单创建时间',
    order_update_time       datetime                 default CURRENT_TIMESTAMP null comment '订单修改时间'
)
    comment '订单表';

