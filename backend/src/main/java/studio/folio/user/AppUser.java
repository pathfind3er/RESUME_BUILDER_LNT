package studio.folio.user;
import org.springframework.data.annotation.Id;import org.springframework.data.mongodb.core.index.Indexed;import org.springframework.data.mongodb.core.mapping.Document;import java.time.Instant;
@Document("users") public class AppUser { @Id public String id; public String name; @Indexed(unique=true) public String email; public String passwordHash; public boolean emailVerified=true; public boolean premium=false; public Instant createdAt=Instant.now(); }

