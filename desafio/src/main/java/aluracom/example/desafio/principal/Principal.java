package aluracom.example.desafio.principal;
import aluracom.example.desafio.model.*;
import aluracom.example.desafio.repositorio.Autorrepository;
import aluracom.example.desafio.repositorio.Librorepository;
import aluracom.example.desafio.servicios.ConsumoAPI;
import aluracom.example.desafio.servicios.Conversor;

import java.util.*;


public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();

    private Conversor conversor = new Conversor();

    private Scanner teclado = new Scanner(System.in);

    private final String URL_BASE = "https://gutendex.com/books/";

    List<Libro> listaLibros = new ArrayList<>();

    List<Autor> listaAutores = new ArrayList<>();

    List<Libro> librosAutores = new ArrayList<>();

    List<Autor> autoresVivos = new ArrayList<>();

    List<Libro> librosPorIdioma  = new ArrayList<>();

    private Librorepository librorepositorio;

    private Autorrepository autorrepositorio;


    public Principal(Librorepository librorepository, Autorrepository autorrepository) {
        this.librorepositorio = librorepository;
        this.autorrepositorio = autorrepository;
    }

    public Principal() {

    }


    public void MuestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1. Buscar libros por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un año
                    5. Listar libros por idioma
                    0. Salir 
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    mostrarDatosLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrado();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                default:
                    break;
            }
        }

    }

    private void mostrarDatosLibro() {
        System.out.println("Ingrese el libro que desea buscar");
        var NombreLibro = teclado.nextLine();
        try {
            var json = consumo.obtenerDatos(URL_BASE + "?search=" + NombreLibro.replace(" ", "+"));
            Datos datosBusqueda = conversor.convierteDatos(json, Datos.class);

            if (!datosBusqueda.libros().isEmpty()) {
                DatosLibro datosLibro = datosBusqueda.libros().get(0);
                Libro libro = new Libro(datosLibro); // convierto a Libro

                for (DatosAutor datosAutor : datosLibro.autores()) {
                    Autor autor = new Autor(datosAutor); // convierto a Autor

                    Optional<Autor> autorExistente = autorrepositorio.findAutorByNombre(autor.getNombre());
                    if (autorExistente.isPresent()) {
                        libro.setAutor(autorExistente.get());  //asigna el autor al libro  si esta presente en la BD
                    } else {
                        autorrepositorio.save(autor); // guardo el autor en la base de datos
                        libro.setAutor(autor); //asigna el autor al libro  si esta presente en la BD
                    }
                }
                listaLibros.add(libro);  // agrega el libro a la lista local de libros
                librorepositorio.save(libro); // guarda el libro en la base de datos
                System.out.println(libro); // imprimo en pantalla el libro buscado en la API
            }

        } catch (Exception e) {
            System.out.println("Error al convertir los libros" + e.getMessage());
            e.printStackTrace();
        }

    }
    private void mostrarLibrosRegistrado() {
        listaLibros = librorepositorio.findAll();
        for (Libro libro : listaLibros) {
            libro.imprimirDatosLibro();

        }

    }
    private void mostrarAutoresRegistrados() {
        listaAutores = autorrepositorio.findAll();
        for(Autor autor : listaAutores) {
            librosAutores = librorepositorio.findLibrosByAutor(autor);
            autor.imprimirDatosAutor();
        }

           }
    private void mostrarAutoresVivos(){
        System.out.println("Ingrese año que desee consultar: ");
        int fechaUsuario = Integer.parseInt(teclado.nextLine());
        autoresVivos = autorrepositorio.findAutorByAñoMuerte(fechaUsuario);
        for (Autor autor : autoresVivos) {
            System.out.println(autor.getNombre().toString().trim());
        }
    }
    private void mostrarLibrosPorIdioma() {
        System.out.println("Ingrese idioma del libro: ");
        var idiomaUsuario = teclado.nextLine();
        librosPorIdioma = librorepositorio.findAllByIdiomas(idiomaUsuario);
        for (Libro libro : librosPorIdioma) {
            System.out.println(libro.getTitulo().toString().trim());
        }
    }


    }















































