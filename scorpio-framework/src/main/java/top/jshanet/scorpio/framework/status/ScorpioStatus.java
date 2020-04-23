package top.jshanet.scorpio.framework.status;

import top.jshanet.scorpio.framework.core.status.StatusRepository;

public interface ScorpioStatus {

    default String getMsg() {
        return StatusRepository.get(this).getMsg();
    }

    default String getCode() {
        return StatusRepository.get(this).getCode();
    }

    enum General implements ScorpioStatus {

        @Status(code = 0, msg = "success") SUCCESS,
        @Status(code = -1, msg = "unknown error") UNKNOWN_ERROR,
        @Status(code = -2, msg = "system timeout") SYSTEM_TIMEOUT,
        @Status(code = -3, msg = "bad request") BAD_REQUEST

    }

}