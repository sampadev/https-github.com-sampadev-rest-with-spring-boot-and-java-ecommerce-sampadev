package br.com.sampa.sampastore.config;

import br.com.sampa.sampastore.entity.Produto;
import br.com.sampa.sampastore.entity.ImagemProduto;
import br.com.sampa.sampastore.service.ProdutoService;
import br.com.sampa.sampastore.service.ArmazenamentoImagemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoDataLoader implements CommandLineRunner {

    private final ProdutoService produtoService;
    private final ArmazenamentoImagemService armazenamentoImagemService;
    private final ResourceLoader resourceLoader;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ProdutoDataLoader(ProdutoService produtoService,
                             ArmazenamentoImagemService armazenamentoImagemService,
                             ResourceLoader resourceLoader) {
        this.produtoService = produtoService;
        this.armazenamentoImagemService = armazenamentoImagemService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ProdutoData> produtosIniciais = Arrays.asList(
                new ProdutoData(
                        "iPhone 16 Pro Max",
                        "Um smartphone potente com câmera de alta resolução e bateria de longa duração.",
                        new BigDecimal("8999.99"),
                        50,
                        new BigDecimal("4.8"),
                        Arrays.asList("img/smartphone_1.jpg", "img/smartphone_2.jpg", "img/smartphone_3.jpg")
                ),
                new ProdutoData(
                        "Apple notebook MacBook Air - Cinza espacial",
                        "Ideal para trabalho e estudo, com processador rápido e design elegante.",
                        new BigDecimal("5999.00"),
                        30,
                        new BigDecimal("4.5"),
                        Arrays.asList("img/notebook_1.jpg", "img/notebook_2.jpg", "img/notebook_3.jpg")
                ),
                new ProdutoData(
                        "Apple AirPods Max - Meia-noite",
                        "Áudio imersivo e cancelamento de ruído para uma experiência sonora incrível.",
                        new BigDecimal("4418.90"),
                        100,
                        new BigDecimal("4.7"),
                        Arrays.asList("img/fone_1.jpg", "img/fone_2.jpg", "img/fone_3.jpg")
                ),
                new ProdutoData(
                        "Apple Watch Series 10 GPS",
                        "Monitore sua saúde e atividades físicas com estilo e precisão.",
                        new BigDecimal("4226.00"),
                        75,
                        new BigDecimal("4.6"),
                        Arrays.asList("img/smartwatch_1.jpg", "img/smartwatch_2.jpg", "img/smartwatch_3.jpg")
                ),
                new ProdutoData(
                        "Câmera Canon EOS Rebel T7+ EF-S 18-55 f/3.5-5.6 IS II BR",
                        "Capture momentos inesquecíveis com qualidade de imagem superior.",
                        new BigDecimal("1500.50"),
                        20,
                        new BigDecimal("4.9"),
                        Arrays.asList("img/camera_1.jpg", "img/camera_2.jpg", "img/camera_3.jpg")
                ),
                new ProdutoData(
                        "Apple 2024 Mac mini",
                        "Desktop com desenpenho de sobra..",
                        new BigDecimal("7499.50"),
                        20,
                        new BigDecimal("4.9"),
                        Arrays.asList("img/desk_1.jpg", "img/desk_2.jpg", "img/desk_3.jpg")
                )

        );

        for (ProdutoData data : produtosIniciais) {

            Optional<Produto> produtoExistente = produtoService.findByNome(data.nome);
            if (produtoExistente.isPresent()) {
                System.out.println("Produto '" + data.nome + "' já existe. Ignorando criação.");
                continue;
            }

            Produto produto = Produto.builder()
                    .nome(data.nome)
                    .descricaoDetalhada(data.descricaoDetalhada)
                    .preco(data.preco)
                    .quantidadeEstoque(data.quantidadeEstoque)
                    .avaliacao(data.avaliacao)
                    .ativo(true)
                    .build();

            List<MultipartFile> imagensMultipart = new ArrayList<>();
            for (String imagemPath : data.imagePaths) {
                try {
                    Resource resource = resourceLoader.getResource("classpath:" + imagemPath);
                    InputStream inputStream = resource.getInputStream();
                    byte[] bytes = inputStream.readAllBytes();
                    String fileName = Paths.get(imagemPath).getFileName().toString();
                    String contentType = Files.probeContentType(resource.getFile().toPath());
                    if (contentType == null) {
                        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) contentType = "image/jpeg";
                        else if (fileName.endsWith(".png")) contentType = "image/png";
                        else if (fileName.endsWith(".gif")) contentType = "image/gif";
                        else contentType = "application/octet-stream";
                    }

                    imagensMultipart.add(new MockMultipartFile(fileName, fileName, contentType, bytes));
                } catch (IOException e) {
                    System.err.println("Erro ao carregar a imagem do classpath: " + imagemPath + ". Erro: " + e.getMessage());
                    continue;
                }
            }

            if (!imagensMultipart.isEmpty()) {
                try {
                    Produto produtoSalvo = produtoService.salvarProduto(produto, imagensMultipart.toArray(new MultipartFile[0]));
                    System.out.println("Produto '" + produtoSalvo.getNome() + "' e suas imagens criados com sucesso!");
                } catch (IllegalArgumentException e) {
                    // Captura exceções específicas de validação, como nome duplicado
                    System.err.println("Erro de validação ao salvar o produto '" + produto.getNome() + "': " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Erro inesperado ao salvar o produto '" + produto.getNome() + "': " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.err.println("Nenhuma imagem válida encontrada para o produto '" + data.nome + "'. Produto não será criado.");
            }
        }
    }

    private static class ProdutoData {
        String nome;
        String descricaoDetalhada;
        BigDecimal preco;
        Integer quantidadeEstoque;
        BigDecimal avaliacao;
        List<String> imagePaths;

        public ProdutoData(String nome, String descricaoDetalhada, BigDecimal preco, Integer quantidadeEstoque, BigDecimal avaliacao, List<String> imagePaths) {
            this.nome = nome;
            this.descricaoDetalhada = descricaoDetalhada;
            this.preco = preco;
            this.quantidadeEstoque = quantidadeEstoque;
            this.avaliacao = avaliacao;
            this.imagePaths = imagePaths;
        }
    }
}