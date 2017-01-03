package hello.session;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
public class AppController {
    private final SessionID sessionID;
    private final PersonRepository repository;

    @Inject
    public AppController(SessionID sessionID, PersonRepository repository) {
        this.sessionID = sessionID;
        this.repository = repository;
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam(value = "id", required = false, defaultValue = "defaultUser") String id) {
        sessionID.setId(id);
        return id;
    }

    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> logout() {
        sessionID.clearId();
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Person>> findByLastName(
            @RequestParam(value = "lastname", required = false, defaultValue = "") String name) {
        return sessionID.ifAuthenticated(ignore -> {
            if (name.isEmpty()) {
                return repository.findAll();
            } else {
                return repository.findByLastName(name);
            }
        });
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return sessionID.ifAuthenticated(ignore ->
                repository.save(person)
        );
    }
}
