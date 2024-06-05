package aluracom.example.desafio.repositorio;

import aluracom.example.desafio.model.Autor;
import aluracom.example.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Librorepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findLibrosByAutor(@Param("autor" )Autor autor);

    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idiomaUsuario")
    List<Libro> findAllByIdiomas(@Param("idiomaUsuario" ) String idiomas);


    }


