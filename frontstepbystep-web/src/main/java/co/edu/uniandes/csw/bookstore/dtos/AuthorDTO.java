/*
 * AuthorDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
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

    /**
     * Constructor por defecto
     */
    public AuthorDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la book
     * @param name nombre de la book
     */
    public AuthorDTO(AuthorEntity authorE) {
        this.id = authorE.getId();
        this.name = authorE.getName();
        this.birthDate = authorE.getBirthDate();
    }

    public AuthorEntity toEntity() {
        AuthorEntity authorE = new AuthorEntity();
        authorE.setId(this.id);
        authorE.setName(this.name);
         authorE.setBirthDate(this.birthDate);
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

   
}
