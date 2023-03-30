package fr.ulco.springshop.model.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductForm {
    private String name;
    private float price;

    private int quantity;

    private String description;

    @Nullable
    private MultipartFile thumbnail = null;

    private List<String> categories;

    private boolean isHighlighted;

}
