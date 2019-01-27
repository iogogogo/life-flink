package com.iogogogo.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public class IoUtils {

    public static void close(Closeable... closeables) {
        if (closeables == null) return;
        try {
            for (Closeable io : closeables) {
                if (io != null) io.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
