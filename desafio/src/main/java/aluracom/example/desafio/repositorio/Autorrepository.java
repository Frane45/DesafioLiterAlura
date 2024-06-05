package aluracom.example.desafio.repositorio;

import aluracom.example.desafio.model.Autor;
import aluracom.example.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Autorrepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findAutorByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.añoNacimiento <= :fechaUsuario AND (a.añoMuerte IS NULL OR a.añoMuerte >= :fechaUsuario)")
    List<Autor> findAutorByAñoMuerte(@Param("fechaUsuario") int fechaUsuario);

    }
