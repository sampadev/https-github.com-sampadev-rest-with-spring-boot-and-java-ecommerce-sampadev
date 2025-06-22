package br.com.sampa.sampastore.repository;

import br.com.sampa.sampastore.entity.ImagemProduto;
import br.com.sampa.sampastore.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
    List<ImagemProduto> findByProdutoProdutoId(Long produtoId);

    @Modifying
    @Query("UPDATE ImagemProduto i SET i.imagemPrincipal = false WHERE i.produto.produtoId = :produtoId")
    void updateAllImagensNotPrincipal(@Param("produtoId") Long produtoId);

    @Query("SELECT i FROM ImagemProduto i WHERE i.produto.produtoId = :produtoId ORDER BY i.ordem ASC")
    List<ImagemProduto> findByProdutoOrderByOrdem(@Param("produtoId") Long produtoId);

}