/*
 * ReviewResource.java
 * Clase que representa el recurso "/books/idBook/reviews"
 * Implementa varios métodos para manipular las books
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.ejb.ReviewLogic;
import co.edu.uniandes.csw.bookstore.dtos.ReviewDTO;
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "reviews".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "reviews". Al ejecutar la aplicación, el recurse
 * será accesible a través de la ruta "/api/books/idBook/reviews"
 *
 */
@Path("books/{bookId: \\d+}/reviews")
@Produces("application/json")
@Consumes("application/json")
public class ReviewResource {

    private static final Logger logger = Logger.getLogger(ReviewLogic.class.getName());
    @Inject
    ReviewLogic reviewLogic;
    @Inject
    BookLogic bookLogic;

    @GET
    public List<ReviewDTO> getReviews(@PathParam("bookId") Long bookId) throws BusinessLogicException {
      
        BookEntity entity = bookLogic.getBook(bookId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
        List<ReviewEntity> list = reviewLogic.getReviews(bookId);
        return listEntity2DTO(list);
    }

    @GET
    @Path("{id: \\d+}")
    public ReviewDTO getReview(@PathParam("bookId") Long bookId, @PathParam("id") Long id) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.getReview(bookId, id));
    }

    @POST
    public ReviewDTO createReview(@PathParam("bookId") Long bookId, ReviewDTO review) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.createReview(bookId, review.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public ReviewDTO updateReview(@PathParam("bookId") Long bookId, @PathParam("id") Long id, ReviewDTO review) throws BusinessLogicException {
        return new ReviewDTO(reviewLogic.updateReview(bookId, id, review.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("bookId") Long bookId, @PathParam("id") Long id) throws BusinessLogicException {
        reviewLogic.deleteReview(bookId, id);
    }

    private List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entityList) {
        List<ReviewDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            list.add(new ReviewDTO(entity));
        }
        return list;
    }

}
