package aluracom.example.desafio.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idiomas;

    private Double numeroDeDescargas;

    public Libro(DatosLibro datosLibro) {

        this.titulo = datosLibro.titulo();

        this.autor = new Autor(datosLibro.autores().get(0));

        this.numeroDeDescargas = datosLibro.numeroDeDescargas();

        this.idiomas = datosLibro.idiomas().get(0).trim();
    }


    public Libro() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;

    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String toString() {

        return  "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
               "Número de descargas: " + numeroDeDescargas + "\n" +
               "Idiomas: " + idiomas;
    }

    public void imprimirDatosLibro() {
        System.out.println("*********LIBRO************");
        System.out.println(this.toString());
    }

}



