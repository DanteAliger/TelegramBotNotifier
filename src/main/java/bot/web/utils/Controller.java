package bot.web.utils;


import bot.service.NotifyService;
import bot.web.request.NotifyRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class Controller {

    @Autowired
    NotifyService notifyService;

    @PostMapping("/event")
    public ResponseEntity<?> notify( @RequestBody NotifyRq notifyRq) {
        notifyService.notifyUser(notifyRq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
