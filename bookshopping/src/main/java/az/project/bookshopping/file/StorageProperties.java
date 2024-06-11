package az.project.bookshopping.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir-book-shopping-spring-ajax";

}
