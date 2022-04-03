package bot.service;

import bot.web.request.EventRq;
import bot.web.request.EventRqUpdate;
import bot.web.request.PersonRq;
import bot.web.request.TemplateRq;
import bot.web.response.EventResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Value(value = "${url.event.post}")
    private String URL_POST_EVENT;
    @Value(value = "${url.event.delete}")
    private  String URL_DELETE_EVENT ;
    @Value(value = "${url.event.all}")
    private  String URL_GET_EVENT_ALL;

    public boolean createEvent(PersonRq personRq, TemplateRq templateRq, EventRq eventRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<EventRq> entity = new HttpEntity<>(eventRq,headers);
            RestTemplate restTemplate = new RestTemplate();
            if (restTemplate.exchange(String.format(URL_POST_EVENT, personRq.getIdTelegram(), templateRq.getName()), HttpMethod.POST, entity, EventResponse.class).getStatusCode() == HttpStatus.CREATED){
                return true;
            }else
                return false;
        }catch (HttpClientErrorException.Conflict e){
            return false;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteEvent(PersonRq personRq, TemplateRq templateRq, Long idEvent){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> entity = new HttpEntity<>(idEvent,headers);
            RestTemplate restTemplate = new RestTemplate();
            URIBuilder builder = new URIBuilder(String.format(URL_DELETE_EVENT, personRq.getIdTelegram(), templateRq.getId()));
            builder.addParameter("idEvent", String.valueOf(idEvent));
            if (restTemplate.exchange(builder.build(), HttpMethod.DELETE, entity, String.class).getStatusCode() == HttpStatus.OK){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<List<EventResponse>> allEvent(PersonRq personRq, TemplateRq templateRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<EventRq> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            return  Optional.ofNullable(restTemplate.exchange(String.format(URL_GET_EVENT_ALL, personRq.getIdTelegram(), templateRq.getName()), HttpMethod.GET, entity, new ParameterizedTypeReference<List<EventResponse>>() {}).getBody());
        }catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
