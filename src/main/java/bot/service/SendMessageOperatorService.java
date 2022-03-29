package bot.service;

import bot.constant.Event;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static bot.constant.Const.*;
import static bot.constant.Event.*;
import static java.util.Arrays.asList;

@Service
public class SendMessageOperatorService {

    private final ButtonService buttonService = new ButtonService();

    public  SendMessage subscribe(Update update, String info ){ return createMessage(update, info, asList(SUBSCRIBE));}

    public SendMessage createGreetingInformation(Update update){ return createMessage(update, String.format(INFO_BOT, update.getMessage().getFrom().getFirstName()),asList(START_PLANING, START_WORK));}

    public SendMessage event(Update update, String templateSelect , boolean info){ return info ? createMessage(update,  String.format(CREATION_EVENT, templateSelect), asList(ADD_EVENT, SHOW_EVENT, BACK)) : createMessage(update, String.format(CREATION_ERROR_EVENT, templateSelect), asList(ADD_EVENT, SHOW_EVENT, BACK));}

    public SendMessage event(Update update, String templateSelect){ return createMessage(update, String.format(ACTION_EVENT, templateSelect), asList(ADD_EVENT, SHOW_EVENT, BACK));}

    public SendMessage createEvent(Update update, String info){ return createMessage(update, info, asList(CANCEL_EVENT));}

    public SendMessage createEventReplay(Update update, String info){ return createMessage(update, info, asList(YES, NOT, CANCEL_EVENT));}

    public SendMessage createEventWeek(Update update, String info){ return createMessage(update, info, asList(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY,CANCEL_EVENT));}

    public SendMessage showEvent(Update update, String events){ return createMessage(update, events, asList(SELECT_EVENT, DELETE_EVENT, CANCEL_EVENT));}

    public SendMessage showEvent(Update update, boolean info){ return  info ? createMessage(update,INFO_DONE_DELETE_EVENT , asList(SELECT_EVENT, DELETE_EVENT, CANCEL_EVENT)) : createMessage(update,ERROR_DELETE_EVENT , asList(SELECT_EVENT, DELETE_EVENT, CANCEL_EVENT));}

    public SendMessage selectShowEvent(Update update, String events, String info){ return createMessage(update, events, asList(SELECT_EVENT, DELETE_EVENT, CANCEL_EVENT));}

    public SendMessage template(Update update, String templates){ return  createMessage(update, String.format(ACTION_TEMPLATE, templates), asList(ADD_TIMETABLE, DELETE_TEMPLATE, SELECT_TIMETABLE, BACK));}

    public SendMessage templateSelectAddEvent(Update update, String templates){ return  createMessage(update, String.format(templates.concat(TEMPLATE_SELECT_ADD_EVENT)), asList(BACK));}

    public SendMessage template(Update update, boolean info){ return info ? createMessage(update, CREATION_TEMPLATE, asList(ADD_TIMETABLE,DELETE_TEMPLATE, SELECT_TIMETABLE, BACK)) : createMessage(update, CREATION_ERROR_TEMPLATE, asList(ADD_TIMETABLE,DELETE_TEMPLATE, SELECT_TIMETABLE, BACK)); }

    public SendMessage back(Update update){ return createGreetingInformation(update);}

    public SendMessage cancel(Update update, String info){ return  subscribe(update, info);}

    public SendMessage createTemplate(Update update){ return createMessage(update, INFO_ADD_TEMPLATE, List.of(CANCEL_TEMPLATE));}

    public SendMessage deleteTemplate(Update update){ return createMessage(update,INFO_DELETE_TEMPLATE, List.of(CANCEL_TEMPLATE));}

    public SendMessage selectTemplate(Update update){ return createMessage(update,INFO_SELECT_TEMPLATE, List.of(CANCEL_TEMPLATE));}

    public SendMessage selectEvent(Update update, String info){ return createMessage(update,info, List.of(CANCEL_EVENT));}

    public SendMessage createNewTemplate(Update update){ return  createMessage(update, ACTION_TEMPLATE, asList(ADD_TIMETABLE, DELETE_TEMPLATE, SELECT_TIMETABLE, BACK));}

    public SendMessage createEvent(Update update){ return createMessage(update, INFO_ADD_TEMPLATE, List.of(CANCEL_EVENT));}



    private SendMessage createMessage(Update update, String message, List<Event> listEvents){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(message);
        ReplyKeyboardMarkup replyKeyboardMarkup = buttonService.setButton(buttonService.createButton(listEvents));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }




}
