package transargent.transargent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transargent.transargent.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
