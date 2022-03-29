package bot;

import bot.constant.Const;
import bot.constant.Event;
import bot.service.EventService;
import bot.web.Display.EventDisplay;
import bot.web.mapper.EventMapper;
import bot.web.request.EventAddRq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import bot.service.PersonService;
import bot.service.SendMessageOperatorService;
import bot.service.TemplateService;
import bot.web.mapper.PersonMapper;
import bot.web.request.TemplateRq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static bot.constant.Const.*;

@Slf4j
@Component
public class CoreBot extends TelegramLongPollingBot {

    EventAddRq eventAddRq = new EventAddRq();

    private static final Map<Long,Branch> BRANCH_MAP = new ConcurrentHashMap<>();

    @Value("${telegram.name}")
    private String botName;
    @Value("${telegram.token}")
    private String botToken;

    @Autowired
    private EventService eventService;
    @Autowired
    private PersonService personService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    SendMessageOperatorService sendMessageOperatorService;

    @Override
    public String getBotUsername() {
        return botName;
    }
    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Branch branch=getBranch(update.getMessage().getFrom().getId());

        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (Event.of(update.getMessage().getText())) {
                case START_WORK -> {
                    run(sendMessageOperatorService.templateSelectAddEvent(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                    branch.setWriting(true).setEvent(Event.SELECT_TIMETABLE_ADD_EVENT);
                }
                case START_PLANING -> {
                    run(sendMessageOperatorService.template(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                }

                case SUBSCRIBE -> {
                    if (personService.createPerson(PersonMapper.toRq(update))) {
                        run(sendMessageOperatorService.createGreetingInformation(update));
                    }else
                        run(sendMessageOperatorService.subscribe(update, CREATION_ERROR));
                }

                case ADD_TIMETABLE -> {
                    branch.setWriting(true).setEvent(Event.ADD_TIMETABLE);
                    run(sendMessageOperatorService.createTemplate(update));
                }

                case DELETE_TEMPLATE -> {
                    run(sendMessageOperatorService.deleteTemplate(update));
                    branch.setWriting(true).setEvent(Event.DELETE_TEMPLATE);
                }

                case SELECT_TIMETABLE -> {
                    run(sendMessageOperatorService.selectTemplate(update));
                    branch.setWriting(true).setEvent(Event.SELECT_TIMETABLE);
                }

                case BACK -> run(sendMessageOperatorService.back(update));

                case CANCEL_TEMPLATE -> {
                    branch.setWriting(false);
                    run(sendMessageOperatorService.template(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                }

                case CANCEL_EVENT -> {
                    branch.setWriting(false);
                    run(sendMessageOperatorService.event(update, branch.getSelectTemplate().getName()));
                }

                case ADD_EVENT -> {
                    branch.setWriting(true).setEvent(Event.ADD_EVENT).setAddEvent(Event.ADD_EVENT_TEXT);
                    run(sendMessageOperatorService.createEvent(update, Const.INFO_ADD_EVENT_TEXT));
                }
                case SHOW_EVENT -> {
                    branch.setWriting(false).setEvents(branch.listToMapEvent(eventService.allEvent(PersonMapper.toRq(update),
                            new TemplateRq(branch.getSelectTemplate().getName())).get()));
                    run(sendMessageOperatorService.showEvent(update, EventDisplay.mapToString(branch.getEvents())));
                }
                case SELECT_EVENT -> {
                    branch.setWriting(true).setEvent(Event.SELECT_EVENT);
                    run(sendMessageOperatorService.selectEvent(update, INFO_SELECT_EVENT));
                }
                case DELETE_EVENT -> {
                    branch.setWriting(true).setEvent(Event.DELETE_EVENT);
                    run(sendMessageOperatorService.selectEvent(update, INFO_DELETE_EVENT));
                }

                default -> {
                    if(branch.isWriting()){
                        switch (branch.getEvent()){
                            case SELECT_TIMETABLE_ADD_EVENT -> {
                                branch.setWriting(false).setSelectTemplate(new TemplateRq(branch.getTemplateAll().get(update.getMessage().getText()).getId(),branch.getTemplateAll().get(update.getMessage().getText()).getNameTemplate()));
                                run(sendMessageOperatorService.event(update, branch.getSelectTemplate().getName()));
                            }
                            case ADD_TIMETABLE -> {
                                run(sendMessageOperatorService.template(update,
                                        templateService.createTemplate(PersonMapper.toRq(update),
                                                new TemplateRq(update.getMessage().getText()))));
                                run(sendMessageOperatorService.template(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                                branch.setWriting(false);
                            }
                            case DELETE_TEMPLATE -> {
                                run(sendMessageOperatorService.template(update,
                                        templateService.deleteTemplate(PersonMapper.toRq(update),
                                                new TemplateRq(branch.getTemplateAll().get(update.getMessage().getText()).getNameTemplate()))));
                                run(sendMessageOperatorService.template(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                                branch.setWriting(false);
                            }
                            case SELECT_TIMETABLE -> {
                                run(sendMessageOperatorService.template(update,
                                        templateService.selectTemplate(PersonMapper.toRq(update),
                                                new TemplateRq(branch.getTemplateAll().get(update.getMessage().getText()).getNameTemplate()))));
                                run(sendMessageOperatorService.template(update, branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get()))));
                                branch.setWriting(false);
                            }
                            case DELETE_EVENT -> {
                                run(sendMessageOperatorService.showEvent(update,
                                        eventService.deleteEvent(
                                                PersonMapper.toRq(update),
                                                branch.getSelectTemplate(),
                                                branch.getEvents().get(update.getMessage().getText()).getId())));

                                branch.setWriting(false).setEvents(branch.listToMapEvent(eventService.allEvent(PersonMapper.toRq(update),
                                        new TemplateRq(branch.getSelectTemplate().getName())).get()));
                                run(sendMessageOperatorService.showEvent(update, EventDisplay.mapToString(branch.getEvents())));
                            }
                            case ADD_EVENT -> {
                                switch (branch.getAddEvent()) {
                                    case ADD_EVENT_TEXT -> {
                                        eventAddRq.setText(update.getMessage().getText());
//                                        branch.getEventAddRq().setText(update.getMessage().getText());
                                        branch.setWriting(true).setEvent(Event.ADD_EVENT).setAddEvent(Event.ADD_EVENT_REPEATABLE);
                                        run(sendMessageOperatorService.createEventReplay(update, Const.INFO_ADD_EVENT_PERIOD));
                                    }
                                    case ADD_EVENT_REPEATABLE -> {
                                        eventAddRq.setRepeatable(update.getMessage().getText());
//                                        branch.getEventAddRq().setRepeatable(update.getMessage().getText());
                                        branch.setWriting(true).setEvent(Event.ADD_EVENT).setAddEvent(Event.ADD_EVENT_TIME);
                                        run(sendMessageOperatorService.createEvent(update, Const.INFO_ADD_EVENT_TIME));
                                    }
                                    case ADD_EVENT_TIME -> {
                                        eventAddRq.setTime(update.getMessage().getText());
//                                        branch.getEventAddRq().setTime(update.getMessage().getText());
                                        branch.setWriting(true).setEvent(Event.ADD_EVENT).setAddEvent(Event.ADD_EVENT_DATA);
                                        run(sendMessageOperatorService.createEventWeek(update, Const.INFO_ADD_EVENT_DATA));
                                    }
                                    case ADD_EVENT_DATA -> {
                                        eventAddRq.setData(update.getMessage().getText());
//                                        branch.getEventAddRq().setData(update.getMessage().getText());
                                        run(sendMessageOperatorService.event(
                                                update,
                                                branch.mapToString(branch.listToMapTemplate(templateService.allTemplate(PersonMapper.toRq(update)).get())),
                                                eventService.createEvent(PersonMapper.toRq(update), new TemplateRq(branch.getSelectTemplate().getName()), EventMapper.toRq(eventAddRq))));
                                        branch.setWriting(false);
                                    }
//                                    run(sendMessageOperatorService.template(update,
//                                            EventService.createEvent(PersonMapper.toRq(update.getMessage().getFrom()),
//                                                    new TemplateRq(update.getMessage().getText()))));
//                                    branch.setWriting(false);
                                }
                            }
                        }

                    }else {
                        if (personService.findByTelegramId(update.getMessage().getFrom().getId()).isEmpty()) {
                            run(sendMessageOperatorService.subscribe(update, GREETING_MESSAGE));
                        } else {
                            log.info("Подписка");
                            getBranch(update.getMessage().getFrom().getId());
                            run(sendMessageOperatorService.createGreetingInformation(update));
                        }
                    }
                }
            }
        }
    }

    private Branch getBranch (Long id){
        return BRANCH_MAP.putIfAbsent(id, new Branch());
    }

    public void run(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
