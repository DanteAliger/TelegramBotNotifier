package bot.service;

import bot.CoreBot;
import bot.web.request.NotifyRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class NotifyService {

    @Autowired
    CoreBot coreBot;

    public void notifyUser(NotifyRq notifyRq){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(notifyRq.getIdTelegram().toString());
            sendMessage.setText(notifyRq.getTextEvent());
            coreBot.run(sendMessage);
    }

}
