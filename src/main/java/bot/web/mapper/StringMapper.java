package bot.web.mapper;

import bot.constant.Status;
import bot.web.response.EventResponse;
import bot.web.response.TemplateResponse;

import java.util.Map;

public interface StringMapper {

    static String eventMapToString(Map<String, EventResponse> events){
        return
                "Активные:\n".concat(events.entrySet().stream()
                                .filter(t-> t.getValue().getStatus()== Status.ACTIVE)
                                .map(t-> t.getKey().concat(" - "+t.getValue().getText() + "\n")
                                        .concat("Повтор: "+t.getValue().getRepeatable()+ "\n")
                                        .concat("Дата: "+t.getValue().getNextExecution()+ "\n"))

                                .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутствуют.")).concat(
                                "\n\nНеактивные:\n".concat(events.entrySet().stream()
                                        .filter(t-> t.getValue().getStatus()== Status.COMPLETED)
                                        .map(t-> t.getKey().concat(" - "+t.getValue().getText() + "\n")
                                                .concat("Повтор: "+t.getValue().getRepeatable()+ "\n")
                                                .concat("Дата: "+t.getValue().getNextExecution()+ "\n"))
                                        .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутствуют.")))
                        .concat("\n");
    }

    static String templateMapToString(Map<String , TemplateResponse> templateAll){
        return
                "Активные:\n".concat(templateAll.entrySet().stream()
                                .filter(t-> t.getValue().getStatus()== Status.ACTIVE)
                                .map(t-> t.getKey().concat(" - "+t.getValue().getNameTemplate()))
                                .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутствуют.")).concat(
                                "\n\nНеактивные:\n".concat(templateAll.entrySet().stream()
                                        .filter(t-> t.getValue().getStatus()== Status.COMPLETED)
                                        .map(t-> t.getKey().concat(" - "+t.getValue().getNameTemplate()))
                                        .reduce((t1, t2)-> t1.concat("\n"+t2)).orElse("Отсутствуют.")))
                        .concat("\n");
    }
}
