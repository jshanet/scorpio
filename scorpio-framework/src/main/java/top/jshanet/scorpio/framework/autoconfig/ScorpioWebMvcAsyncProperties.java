package top.jshanet.scorpio.framework.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author seanjiang
 * @since 2020-07-14
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "scorpio.webmvc.async")
public class ScorpioWebMvcAsyncProperties {

    @Getter
    @Setter
    public static class ThreadPoolProperties {
        private int coolPoolSize = 10;

        private int maxPoolSize = 50;

        private int queueCapacity = 200;

        private int keepAliveSeconds = 60;

        private String threadNamePrefix = "scorpioWebTask-";

        private boolean allowCoreThreadTimeOut = true;

        private boolean waitForTasksToCompleteOnShutdown = true;

        private int awaitTerminationSeconds = 60;

    }

    private ThreadPoolProperties threadPool = new ThreadPoolProperties();

    private long timeout = 100000L;

    private boolean enableGlobalRestMessage = true;

}
