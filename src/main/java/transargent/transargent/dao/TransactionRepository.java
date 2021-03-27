package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
