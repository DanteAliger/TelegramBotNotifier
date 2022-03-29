package bot;

import bot.constant.Event;
import bot.constant.Status;
import bot.web.request.EventAddRq;
import bot.web.request.TemplateRq;
import bot.web.response.EventResponse;
import bot.web.response.TemplateResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Accessors(chain = true)
@Getter
@Setter
//@Scope(value = "session")
public class Branch {
    private boolean writing = false;
    private Event event;
    private Event addEvent;
    private EventAddRq eventAddRq;
    private TemplateRq selectTemplate;
    private Map<String, EventResponse> events;
    private Map<String , TemplateResponse> templateAll;

    public String mapToString(Map<String , TemplateResponse> templateAll){
        return
                "Активные:\n".concat(templateAll.entrySet().stream()
                                .filter(t-> t.getValue().getStatus()== Status.ACTIVE)
                                .map(t-> t.getKey().concat(" - "+t.getValue().getNameTemplate()))
                                .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутсвуют.")).concat(
                                "\n\nНеактивные:\n".concat(templateAll.entrySet().stream()
                                        .filter(t-> t.getValue().getStatus()== Status.COMPLETED)
                                        .map(t-> t.getKey().concat(" - "+t.getValue().getNameTemplate()))
                                        .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутсвуют.")))
                        .concat("\n");
    }

    public Map<String, TemplateResponse> listToMapTemplate(List<TemplateResponse> templateResponseList){
        templateAll = new HashMap<>();
        templateResponseList = templateResponseList.stream().sorted(Comparator.comparing(TemplateResponse::getStatus).thenComparing(TemplateResponse::getNameTemplate)).toList();
        for (TemplateResponse tp: templateResponseList) {
            this.templateAll.put(String.valueOf(templateResponseList.indexOf(tp)+1), tp);
        }
        return templateAll;
    }

    public Map<String, EventResponse> listToMapEvent(List<EventResponse> eventResponseList){
        Map<String, EventResponse> events = new HashMap<>();
        eventResponseList = eventResponseList.stream().sorted(Comparator.comparing(EventResponse::getStatus).thenComparing(EventResponse::getText)).toList();
        for (EventResponse tp: eventResponseList) {
            events.put(String.valueOf(eventResponseList.indexOf(tp)+1), tp);
        }
        return events;
    }


}
