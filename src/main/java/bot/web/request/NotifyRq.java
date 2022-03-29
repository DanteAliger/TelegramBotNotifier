package bot.web.request;

import lombok.Data;

@Data
public class NotifyRq {
    private Long idTelegram;
    private String textEvent;
}
