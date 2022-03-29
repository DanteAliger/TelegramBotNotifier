package bot.web.request;

import bot.constant.Status;
import lombok.Data;

@Data
public class EventRqUpdate extends EventRq{
    private Long ig;
    private Status status;
}
