package test.lowes.urlShortner.lowesurlShortner.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.lowes.urlShortner.lowesurlShortner.model.entity.Url;

@Repository
public interface URLRepository extends JpaRepository<Url, Long> {
}

