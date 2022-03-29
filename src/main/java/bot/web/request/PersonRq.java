package bot.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
