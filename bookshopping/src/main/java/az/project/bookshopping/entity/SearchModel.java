package az.project.bookshopping.entity;

import lombok.Data;
import lombok.ToString;

@ToString

@Data
public class SearchModel {

    private String search;

    private Integer begin;

    private Integer length;
}
