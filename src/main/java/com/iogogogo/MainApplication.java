package com.iogogogo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.iogogogo.conf.AppConf$;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * Created by tao.zeng on 2019/1/21.
 */
public class MainApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        initLogConfig();
        LOGGER.info("hello world");
        LOGGER.info(AppConf$.MODULE$.host());
        LOGGER.info(AppConf$.MODULE$.password());
    }

    private static void initLogConfig() {
        File file = new File(System.getProperty("user.dir") + File.separator + "logback.xml");
        if (file.exists() && file.canRead()) {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            try {
                configurator.doConfigure(file);
            } catch (JoranException e) {
                e.printStackTrace();
            }
        }
    }
}
