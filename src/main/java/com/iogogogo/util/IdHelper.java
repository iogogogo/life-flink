package com.iogogogo.util;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public class IdHelper implements Serializable {

    private final static Snowflake SNOWFLAKE = new Snowflake(1);

    public static long id() {
        return SNOWFLAKE.next();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
