package studio.folio.resume;
import org.springframework.data.mongodb.repository.MongoRepository;import java.util.List;
public interface ResumeRepository extends MongoRepository<ResumeDocument,String>{List<ResumeDocument> findAllByOwnerIdOrderByUpdatedAtDesc(String ownerId);}

