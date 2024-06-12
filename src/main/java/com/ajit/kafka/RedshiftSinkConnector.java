package com.ajit.kafka;

import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

@Log4j2
public class RedshiftSinkConnector extends SinkConnector {
    private RedshiftSinkConnectorConfig config;
    private static final String classname = RedshiftSinkConnector.class.getName();
    @Override
    public String version() {
        return VersionUtil.getVersion();
    }


    /**
     * Start this Connector. This method will only be called on a clean Connector, i.e. it has
     * either just been instantiated and initialized or {@link #stop()} has been invoked.
     *
     * @param map configuration settings
     */
    @Override
    public void start(Map<String, String> map) {
        config = new RedshiftSinkConnectorConfig(map);
        lo
//        log.trace("[{}] Entry {}.start, props={}", Thread.currentThread().getId(), classname, map);

        for (final Map.Entry<String, String> entry : map.entrySet()) {
            String value;
            if (entry.getKey().toLowerCase().contains("password")) {
              value = "[hidden]";
            }
            else {
              value = entry.getValue();
            }
//            log.debug("Connector props entry {} : {}", entry.getKey(), value);
        }

//        log.trace("[{}]  Exit {}.start", Thread.currentThread().getId(), classname);

    }

    @Override
    public Class<? extends Task> taskClass() {
        //TODO: Return your task implementation.
        return RedshiftSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        //TODO: Define the individual task configurations that will be executed.

        /**
         * This is used to schedule the number of tasks that will be running. This should not exceed maxTasks.
         */

        throw new UnsupportedOperationException("This has not been implemented.");
    }

    @Override
    public void stop() {
        //TODO: Do things that are necessary to stop your connector.
    }

    @Override
    public ConfigDef config() {
        return RedshiftSinkConnectorConfig.conf();
    }
}
