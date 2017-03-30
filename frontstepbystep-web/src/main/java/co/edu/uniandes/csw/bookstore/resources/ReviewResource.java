/*
 * ReviewResource.java
 * Clase que representa el recurso "/books/idBook/reviews"
 * Implementa varios métodos para manipular las books
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.ReviewLogic;
import co.edu.uniandes.csw.bookstore.dtos.ReviewDTO;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "reviews".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "reviews". Al ejecutar la aplicación, el recurse
 * será accesible a través de la ruta "/api/books/idBook/reviews"
 *
 */

@Produces("application/json")
public class ReviewResource {

    @Inject
    ReviewLogic reviewLogic;

    @GET
    public List<ReviewDTO> getReviews(@PathParam("idBook") Long idBook) throws BusinessLogicException {
        return listEntity2DTO(reviewLogic.getReviews(idBook));
    }

    @GET
    @Path("{id: \\d+}")
    public ReviewDTO getReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.getReview(idBook, id));
    }

    @POST
    public ReviewDTO createReview(@PathParam("idBook") Long idBook, ReviewDTO review) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.createReview(idBook, review.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public ReviewDTO updateReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id, ReviewDTO review) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.updateReview(idBook, id, review.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id) throws BusinessLogicException {
        reviewLogic.deleteReview(idBook, id);
    }
    
    private List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entityList) {
        List<ReviewDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            list.add(new ReviewDTO(entity));
        }
        return list;
    }

}
