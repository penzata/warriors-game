package org.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.trace("tracing");
        log.debug("debugging");
        log.info("info");
        log.warn("warning");
        log.error("error");

        log.info("Logger name: {}", log.getName());
    }
}