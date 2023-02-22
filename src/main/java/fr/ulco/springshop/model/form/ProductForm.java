package fr.ulco.springshop.model.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductForm {
    private String name;
    private float price;

    private int quantity;

    private String description;

    private MultipartFile thumbnail;


}
