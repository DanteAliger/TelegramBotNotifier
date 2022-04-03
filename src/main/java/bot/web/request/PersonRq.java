package bot.web.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PersonRq {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private Long idTelegram;
    private Long idChatTelegram;

}
