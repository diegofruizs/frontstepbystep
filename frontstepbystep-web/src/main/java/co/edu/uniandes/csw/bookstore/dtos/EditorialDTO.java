/*
 * AuthorDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 * @citi Asistente
 */
public class EditorialDTO {

    private Long id;
    private String name;

    /**
     * Constructor por defecto
     */
    public EditorialDTO() {
    }

    public EditorialDTO(EditorialEntity editorial) {
        this.id = editorial.getId();
        this.name = editorial.getName();
    }

    /**
     * @return the id
     */
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

   

    EditorialEntity toEntity() {
       EditorialEntity entity = new EditorialEntity();
       entity.setId(this.id);
       entity.setName(this.name);
       return entity;
    }
}
