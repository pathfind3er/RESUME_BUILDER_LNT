package studio.folio.resume;
import org.springframework.data.annotation.Id;import org.springframework.data.mongodb.core.index.Indexed;import org.springframework.data.mongodb.core.mapping.Document;import java.time.Instant;import java.util.Map;
@Document("resumes") public class ResumeDocument { @Id public String id; @Indexed public String ownerId; public String title; public String theme="Sage"; public String accent="#6e8272"; public Map<String,Object> data; public Instant createdAt=Instant.now(); public Instant updatedAt=Instant.now(); }

