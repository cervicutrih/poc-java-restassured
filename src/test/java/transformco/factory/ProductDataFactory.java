package transformco.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import transformco.dto.ProductDTO;

import java.io.FileInputStream;
import java.io.IOException;

public class ProductDataFactory {

    private static final Faker faker = new Faker();

    public static ProductDTO createProduct() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String baseDir = System.getProperty("user.dir");
        String filePath = baseDir + "/src/test/resources/requestBody/product.json";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            return objectMapper.readValue(fileInputStream, ProductDTO.class);
        }
    }

    public static ProductDTO newProduct() throws IOException {
        ProductDTO newProduct = createProduct();
        newProduct.setNome("Test " + faker.commerce().productName());
        newProduct.setDescricao(faker.commerce().material());
        newProduct.setPreco(faker.number().numberBetween(40, 500));
        newProduct.setQuantidade(faker.number().numberBetween(1, 100));
        return newProduct;
    }
}
