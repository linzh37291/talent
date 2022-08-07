package com.lin.infrastructure.commons.enums;

import lombok.Getter;

/**
 * @author linzihao
 */
@Getter
public enum BizErrorCode {

    SUCCESS("0000", "请求成功"),

    // ========================================================================//
    // 系统错误
    // ========================================================================//
    UNKNOWN_EXCEPTION("9999", "系统未知错误"),
    // 请求校验
    // ========================================================================//
    REQUEST_PARAM_ILLEGAL("0001", "请求参数非法"), REQUEST_SIGNED_INVALID("0002", "验签异常"), REQUEST_LOADING("0003", "网络异常"),
    REQUEST_PARAM_INVALID("0004", "参数验证失败"), SYSTEM_PARAM_ERROR("0101", "系统参数验证失败"),
    SYSTEM_PARAM_TIMESTAMP_EXPIRE("0102", "系统参数timestamp已经过期"), SYSTEM_PARAM_APPKEY_ERROR("0103", "系统参数app_key无效"),
    SYSTEM_PARAM_SIGN_ERROR("0104", "系统参数sign无效"),

    BAD_REQUEST("0400", "无效的请求"), NO_PERMISSION_ACCESS("0403", "无权限访问"), API_NO_FOUND("0404", "此接口暂未实现"),
    API_METHOD_NOT_SUPPORTED("0405", "此接口不支持此Method"), REQUEST_ACCEPTABLE_NOT_SUPPORTED("0406", "此接口不支持此acceptable"),
    REQUEST_MEDIATYPE_NOT_SUPPORTED("0415", "此接口不支持此mediatype"), REQUEST_TIMEOUT("0503", "请求超时"),
    REQUEST_SYSTEM_ERR("0500", "系统内部错误"), REQUEST_ARE_TOO_FREQUENT("0506", "请求太频繁"),

    // ========================================================================//
    // 交易类错误1XXX
    // ========================================================================//
//    SERVICE_TYPE_ERROR("1001","服务类型有误"),

    // ========================================================================//
    // 服务类错误2XXX
    // ========================================================================//
    SERVICE_TYPE_ERROR("2001", "服务类型有误"), RETURN_MODE_ERROR("2002", "返件方式有误"), NOT_POST_SALE("2003", "申请售后的商品不存在"),
    NOT_EXIST_ORDER_JD("2004", "京东订单信息不存在"), NOT_POST_SALE_PRODUCT("2005", "订单中的商品不可以售后"),
    RETURN_JD_ERROR("2006", "商品返回京东方式有误"), NOT_SUPPORT_POST_SALE("2007", "商品的售后服务类型不支持"),
    NOT_SUPPORT_CHANNEL("2008", "不支持此渠道"), NOT_NULL_ORDER("2009", "订单号不可为空"), NOT_NULL_SKU("2010", "商品编号不可为空"),
    ORDER_REVERSE_RENDER_ERROR("2011", "订单逆向渲染异常"), ORDER_REVERSE_APPLY_ERROR("2012", "订单逆向申请异常"),
    ORDER_DATA_ERROR("2013", "库存不足"), NO_AFTER_SALES("2014", "售后异常"), NO_SERVICE_DETAIL("2015", "暂无服务详情"),
    NO_HBSERVICE_NO("2016", "没有华泊服务单号"), NO_LOGICS_ADDRESS_INFO("2017", "没有物流地址信息"),
    NO_SERVICE_NUMBER("2018", "没有查询到对应的服务订单"), NO_AGAIN_RENDERING("2019", "退款中无法再次渲染"),
    CHECK_ORDER_STATUS("2020", "订单未付款,请检查订单"), NO_FIND_HUABO_SUB_ORDER("2021", "未找到华泊子订单信息"),
    NOT_EXIST_ORDER("2022", "未找到订单信息"), NO_DELIVER_GOODS_NOT_RENDER("2023", "未发货,不允许进行退货退款渲染"),
    CAN_NOT_CANSELL("2024", "商品处于不可售状态"), NOT_ON_SELL_DATE("2025", "商品不在可售档期中"), PRODUCT_NOT_UPDATE("2026", "商品数据未更新"),
    PRODUCT_NOT_EXIST("2027", "商品不存在"), NO_RETURM_ABLE("2028", "该订单对应商品不支持退货或者已发货"),
    ORDER_SELL_OUT("2029", "此商品已售完~您可选购其他商品"), ORDER_ADDRESS_ERROR("2030", "收货地址无法解析");

    private final String code;

    private final String msg;

    BizErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BizErrorCode getByCode(String code) {
        for (BizErrorCode status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
