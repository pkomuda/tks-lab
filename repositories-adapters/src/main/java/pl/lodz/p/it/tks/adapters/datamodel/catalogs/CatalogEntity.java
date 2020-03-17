package pl.lodz.p.it.tks.adapters.datamodel.catalogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Catalog")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract @Data class CatalogEntity {

    @Id
    private int id;
    private String title;
    private int releaseYear;
}
