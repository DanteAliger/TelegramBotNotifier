package bot.web.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TemplateRq {
    private Long id;
    private String name;

    public TemplateRq(String name) {
        this.name = name;
    }

    public TemplateRq( Long id, String name) {
        this.id= id;
        this.name = name;
    }
}
