package hello.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionID implements Serializable {
    private static final long serialVersionUID = 1L;
    private Optional<String> id;

    public SessionID() {
        this.id = Optional.empty();
    }

    public SessionID(String id) {
        this.id = Optional.of(id);
    }

    public Optional<String> getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = Optional.of(id);
    }

    public void clearId() {
        this.id = Optional.empty();
    }

    public <T> ResponseEntity<T> ifAuthenticated(Function<String, T> sessionScope) {
        return getId().map(id -> ResponseEntity.ok(sessionScope.apply(id)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
    }
}


