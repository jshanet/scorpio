package top.jshanet.scorpio.framework.enumerate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author seanjiang
 * @since 2020-07-13
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    String name();

    String value();

}
