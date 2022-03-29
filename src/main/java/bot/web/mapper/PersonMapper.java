package bot.web.mapper;

import bot.web.request.PersonRq;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public interface PersonMapper {

    static PersonRq toRq ( Update update){
        return new PersonRq()
                .setName(update.getMessage().getFrom().getFirstName())
                .setSurname(update.getMessage().getFrom().getLastName())
                .setIdChatTelegram(update.getMessage().getChatId())
                .setIdTelegram(update.getMessage().getFrom().getId());
    }
}
