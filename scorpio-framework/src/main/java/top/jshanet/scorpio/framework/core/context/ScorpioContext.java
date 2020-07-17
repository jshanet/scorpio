package top.jshanet.scorpio.framework.core.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.util.Locale;

/**
 * @author seanjiang
 * @date 2019/12/25
 */
@Getter
@Setter
public class ScorpioContext implements SecurityContext {

    private String requestNo;


    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public void setAuthentication(Authentication authentication) {

    }
}