package bot.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import bot.web.request.PersonRq;
import bot.web.response.PersonResponse;



import java.util.Optional;

@Service
public class PersonService {

    //todo  вынести в файл
    static final String URL_GET_PERSON = "http://localhost:8084/persons/getIdTelegram";
    static final String URL_POST_PERSON = "http://localhost:8084/persons/create";


    public Optional<PersonResponse> findByTelegramId(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            RestTemplate restTemplate = new RestTemplate();
            URIBuilder builder = new URIBuilder(URL_GET_PERSON);
            builder.addParameter("idTelegram", id.toString());
            return  Optional.ofNullable(restTemplate.exchange(builder.build(), HttpMethod.GET, entity, PersonResponse.class).getBody());
        }catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }catch (Exception e){
             throw new RuntimeException(e);
        }
    }

    public boolean createPerson(PersonRq personRq){
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<PersonRq> entity = new HttpEntity<>(personRq,headers);
            RestTemplate restTemplate = new RestTemplate();
            if (restTemplate.exchange(URL_POST_PERSON, HttpMethod.POST, entity, PersonResponse.class).getStatusCode() == HttpStatus.CREATED){
                return true;
            }else
                return false;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }





}
