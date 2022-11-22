package org.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.trace("tracing");
        LOGGER.debug("debugging");
        LOGGER.info("info");
        LOGGER.warn("warning");
        LOGGER.error("error");

        LOGGER.info("Logger name: {}", LOGGER.getName());
    }
}