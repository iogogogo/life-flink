package com.iogogogo;

import com.iogogogo.conf.AppConf$;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by tao.zeng on 2019/1/21.
 */
public class MainApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        LOGGER.info("hello world");
        LOGGER.info(AppConf$.MODULE$.host());
        LOGGER.info(AppConf$.MODULE$.password());
        Config s = AppConf$.MODULE$.loadConfig(null);
        LOGGER.info("{}", s);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
