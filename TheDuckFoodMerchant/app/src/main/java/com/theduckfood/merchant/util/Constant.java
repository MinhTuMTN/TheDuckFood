package com.theduckfood.merchant.util;

public class Constant {
    public static final String BASE_URL = "https://ad0a-113-185-74-169.ngrok-free.app";

    public static final String IMAGE_BASE_URL = BASE_URL + "/api/file/image?id=";

    public static final String GENERAL_NOTIFICATION_CHANNEL_ID = "general_channel_id";
    public static final String GENERAL_NOTIFICATION_CHANNEL_NAME = "General";


    public final static String USER_ACCOUNT_STATUS_ACTIVATED = "activated";
    public final static String USER_ACCOUNT_STATUS_DELETED = "deleted";

    public final static String STORE_STATUS_OPENING = "opening";
    public final static String STORE_STATUS_CLOSED = "closed";
    public final static String STORE_STATUS_DELETED = "deleted";

    public final static String STORE_ACCOUNT_STATUS_ACTIVATED = "activated";
    public final static String STORE_ACCOUNT_STATUS_DELETED = "deleted";

    public final static String FOOD_STATUS_DELETED = "deleted";
    public final static String FOOD_STATUS_SELLING = "selling";
    public final static String FOOD_STATUS_SOLD_OUT = "sold_out";

    public final static String ORDER_STATUS_SUCCESS = "success";
    public final static String ORDER_STATUS_USER_CANCELED = "user_canceled";
    public final static String ORDER_STATUS_PROCESSING = "processing";
    public final static String ORDER_STATUS_STORE_CANCELED = "store_canceled";
    public final static String ORDER_STATUS_SHIPPING= "shipping";

    public final static String COUPON_STATUS_ACTIVATED = "activated";
    public final static String COUPON_STATUS_DELETED = "deleted";

    public final static String DELIVERY_MAN_STATUS_ON = "online";
    public final static String DELIVERY_MAN_STATUS_OFF = "offline";
    public final static String DELIVERY_MAN_STATUS_DELETED = "deleted";

    public final static String DELIVERY_MAN_ACCOUNT_STATUS_ACTIVATED = "activated";
    public final static String DELIVERY_MAN_ACCOUNT_STATUS_DELETED = "deleted";

    public final static Double SHIP_FEE = 15000d;
    public final static Double SERVICE_FEE = 2000d;
}
