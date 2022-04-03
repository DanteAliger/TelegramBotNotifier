package bot.web.response;

import bot.constant.Status;
import lombok.Data;

@Data
public class TemplateResponse {
    private Long id;
    private String nameTemplate;
    private Status status;

}
