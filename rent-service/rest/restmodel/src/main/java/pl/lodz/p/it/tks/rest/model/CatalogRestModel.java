package pl.lodz.p.it.tks.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public abstract @Data class CatalogRestModel {

    @PositiveOrZero
    private int id;
    @NotBlank
    private String title;
    @Positive
    private int releaseYear;
}
