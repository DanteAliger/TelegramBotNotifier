package bot.web.request;

import lombok.Data;
import lombok.experimental.Accessors;


import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class EventRq {
    private String text;
    private Boolean repeatable;
    private LocalDateTime nextExecution;

}
