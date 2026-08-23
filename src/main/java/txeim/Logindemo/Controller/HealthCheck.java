package txeim.Logindemo.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  HealthCheck {
    @GetMapping("/signup-health")
    public ResponseEntity<String> healthcheck(){
        System.out.println("Health is good");
        return ResponseEntity.ok("Health of the server is good");
    }
}
