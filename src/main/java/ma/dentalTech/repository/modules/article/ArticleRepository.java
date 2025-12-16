package ma.dentalTech.repository.modules.article;

import ma.dentalTech.entities.article.Article;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // Trouver les articles actifs
    List<Article> findByActif(Boolean actif);
    
    // Trouver un article par référence
    Article findByReference(String reference);
    
    // Trouver les articles par catégorie
    List<Article> findByCategorie(String categorie);
    
    // Trouver les articles en rupture de stock
    List<Article> findByQuantiteStockLessThan(Integer seuil);
    
    // Trouver les articles sous le seuil d'alerte
    List<Article> findArticlesSousSeuilAlerte();
    
    // Trouver les articles d'un fournisseur
    List<Article> findByFournisseurId(Long fournisseurId);
}

