/*
 * AuthorDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import java.util.Date;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 * @citi Asistente
 */
public class AuthorDTO {

    private Long id;
    private String name;
    private Date birthDate;
    private String description;
    private String image;

    /**
     * Constructor por defecto
     */
    public AuthorDTO() {
    }

   
    public AuthorDTO(AuthorEntity authorE) {
        this.id = authorE.getId();
        this.name = authorE.getName();
        this.birthDate = authorE.getBirthDate();
        this.description = authorE.getDescription();
        this.image = authorE.getImage();
    }

    public AuthorEntity toEntity() {
        AuthorEntity authorE = new AuthorEntity();
        authorE.setId(this.id);
        authorE.setName(this.name);
        authorE.setBirthDate(this.birthDate);
        authorE.setDescription(this.description);
        authorE.setImage(this.image);
        return authorE;
    }

    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
