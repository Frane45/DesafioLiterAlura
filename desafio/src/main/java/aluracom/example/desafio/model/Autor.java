package aluracom.example.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String nombre;

    private int añoNacimiento;

    private int añoMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();

        this.añoNacimiento = datosAutor.añoNacimiento();

        this.añoMuerte = datosAutor.añoMuerte();

    }

    public Autor() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public int getAñoMuerte() {
        return añoMuerte;
    }

    public void setAñoMuerte(int añoMuerte) {
        this.añoMuerte = añoMuerte;
    }

    public Libro getLibro() {
        return libros.get(0);
    }

    public void setLibro(List<Libro> libros) {
        this.libros = libros;
    }

    public String toString() {
        return "Nombre del autor: " + nombre + "\n" +
                "Fecha nacimiento: " + añoNacimiento + "\n" +
                "Fecha de muerte: " + añoMuerte;


    }

    public void imprimirDatosAutor() {
        System.out.println("*********DATOS DEL AUTOR************");
        System.out.println(this.toString());
        if (libros != null && !libros.isEmpty()) {
            for (Libro libro : libros) {
                System.out.println("Título del libro: " + libro.getTitulo());
            }
        } else {
            System.out.println("No hay libros registrados para este autor.");
        }
    }
}
