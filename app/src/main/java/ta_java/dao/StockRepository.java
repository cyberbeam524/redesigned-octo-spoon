package ta_java.dao;

import ta_java.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


  /**
 * Repository class for performing Stock table database updates 
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

  /**
 * Method that returns Stock with unique ticker
 * @param ticker
 * @return Stock with unique ticker
 */
    Stock findByName(String ticker);
}