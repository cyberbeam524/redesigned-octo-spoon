package ta_java.dao;

import ta_java.model.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


  /**
 * Repository class for performing Option table database updates 
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}