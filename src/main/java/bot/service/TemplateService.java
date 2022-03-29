package bot.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import bot.web.request.PersonRq;
import bot.web.request.TemplateRq;
import bot.web.response.TemplateResponse;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {

    static final String URL_POST_TEMPLATE = "http://localhost:8084/persons/%s/template/create";
    static final String URL_DELETE_TEMPLATE = "http://localhost:8084/persons/%s/template/delete";
    static final String URL_SELECT_TEMPLATE = "http://localhost:8084/persons/%s/template/select";
    static final String URL_GET_ALL_TEMPLATE = "http://localhost:8084/persons/%s/template/all";

    public boolean createTemplate(PersonRq personRq, TemplateRq templateRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<TemplateRq> entity = new HttpEntity<>(templateRq,headers);
            RestTemplate restTemplate = new RestTemplate();
            if (restTemplate.exchange(String.format(URL_POST_TEMPLATE, personRq.getIdTelegram()), HttpMethod.POST, entity, TemplateResponse.class).getStatusCode() == HttpStatus.CREATED){
                return true;
            }else
                return false;
        }catch (HttpClientErrorException.Conflict e){
            return false;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTemplate(PersonRq personRq, TemplateRq templateRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<TemplateRq> entity = new HttpEntity<>(templateRq,headers);
            RestTemplate restTemplate = new RestTemplate();
            URIBuilder builder = new URIBuilder(String.format(URL_DELETE_TEMPLATE, personRq.getIdTelegram()));
            builder.addParameter("nameT", templateRq.getName());
            if (restTemplate.exchange(builder.build(), HttpMethod.DELETE, entity, String.class).getStatusCode() == HttpStatus.OK){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean selectTemplate(PersonRq personRq, TemplateRq templateRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<TemplateRq> entity = new HttpEntity<>(templateRq,headers);
            RestTemplate restTemplate = new RestTemplate();
            URIBuilder builder = new URIBuilder(String.format(URL_SELECT_TEMPLATE, personRq.getIdTelegram()));
            builder.addParameter("nameT", templateRq.getName());
            if (restTemplate.exchange(builder.build(), HttpMethod.POST, entity, String.class).getStatusCode() == HttpStatus.OK){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<List<TemplateResponse>> allTemplate(PersonRq personRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<TemplateRq> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            return  Optional.ofNullable(restTemplate.exchange(String.format(URL_GET_ALL_TEMPLATE, personRq.getIdTelegram()), HttpMethod.GET, entity, new ParameterizedTypeReference<List<TemplateResponse>>() {}).getBody());
        }catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
