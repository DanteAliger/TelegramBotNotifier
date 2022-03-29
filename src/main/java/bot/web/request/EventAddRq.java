package bot.web.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventAddRq {
    private String text;
    private String repeatable;
    private String time;
    private String data;
}
