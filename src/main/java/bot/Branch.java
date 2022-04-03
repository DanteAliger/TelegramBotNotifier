package bot;

import bot.constant.Event;
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
public class Branch {
    private boolean writing = false;
    private Event event;
    private Event addEvent;
    private EventAddRq eventAddRq = new EventAddRq();
    private TemplateRq selectTemplate;
    private Map<String, EventResponse> events;
    private Map<String , TemplateResponse> templateAll;


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
