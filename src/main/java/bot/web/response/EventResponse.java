package bot.web.response;

import bot.constant.Status;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String text;
    private Duration periodTimeNotification;
    private Boolean repeatable;
    private LocalDateTime nextExecution;
    private Status status;
}
