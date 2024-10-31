package com.dam2024m8uf1_tfinal.drivera.Singletoon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VideojuegoManager {

    // Instancia única del Singleton
    private static VideojuegoManager instance;

    // Lista de videojuegos
    private List<Videojuego> videojuegos; // Lista que almacenará los videojuegos

    // Videojuego actualmente seleccionado
    private Videojuego videojuegoActual;

    // Constructor privado
    private VideojuegoManager() {
        videojuegos = new ArrayList<>();
        inicializarVideojuegos();
    }
    private void inicializarVideojuegos() {
        añadirVideojuego("The Legend of Zelda: Breath of the Wild", "Aventura", "Switch", "Nintendo", "Nintendo",
                true, true, 59.99, 10.0f, "+7", Calendar.getInstance(), 1, 50, "Basilisk");

        añadirVideojuego("God of War", "Acción/Aventura", "PS4", "Santa Monica Studio", "Sony Interactive Entertainment",
                true, true, 49.99, 9.5f, "+18", Calendar.getInstance(), 1, 30, "Santa Monica");

        añadirVideojuego("Cyberpunk 2077", "RPG", "PC", "CD Projekt Red", "CD Projekt",
                true, true, 59.99, 7.5f, "+18", Calendar.getInstance(), 1, 40, "REDengine 4");

        añadirVideojuego("FIFA 21", "Deportes", "Multi", "EA Sports", "EA",
                true, false, 59.99, 8.0f, "+3", Calendar.getInstance(), 4, 20, "Frostbite");

        añadirVideojuego("Minecraft", "Sandbox", "Multi", "Mojang Studios", "Mojang",
                true, false, 26.95, 9.0f, "TP", Calendar.getInstance(), 1, 100, "Java");
    }

    // Método estático para obtener la instancia única del Singleton
    public static VideojuegoManager getInstance() {
        if (instance == null) {
            instance = new VideojuegoManager();
        }
        return instance;
    }



    // Método para añadir un nuevo videojuego
    public void añadirVideojuego(String titulo, String genero, String plataforma, String desarrollador, String publicador,
                                 boolean esMultijugador, boolean modoHistoria, double precio, float calificacion,
                                 String clasificacionEdad, Calendar fechaLanzamiento, int numeroJugadores,
                                 int duracionPromedio, String motorGrafico) {
        // Creamos un nuevo videojuego y lo añadimos a la lista
        Videojuego nuevoVideojuego = new Videojuego(
                titulo, genero, plataforma, desarrollador, publicador,
                esMultijugador, modoHistoria, precio, calificacion,
                clasificacionEdad, fechaLanzamiento, numeroJugadores,
                duracionPromedio, motorGrafico
        );
        videojuegos.add(nuevoVideojuego); // Añadir el videojuego a la lista
    }

    // Método para obtener la lista de videojuegos
    public List<Videojuego> getVideojuegos() {
        return videojuegos; // Retorna la lista de videojuegos
    }

    // Método para editar atributos específicos del videojuego
    public void editarVideojuego(int id, String titulo, String genero, String plataforma, String desarrollador, String publicador,
                                 Boolean esMultijugador, Boolean modoHistoria, Double precio, Float calificacion,
                                 String clasificacionEdad, Calendar fechaLanzamiento, Integer numeroJugadores,
                                 Integer duracionPromedio, String motorGrafico) {
        // Buscar el videojuego por ID
        for (Videojuego videojuego : videojuegos) {
            if (videojuego.getId() == id) {
                // Si se encuentra, actualizar los atributos
                videojuego.setTitulo(titulo != null ? titulo : videojuego.getTitulo());
                videojuego.setGenero(genero != null ? genero : videojuego.getGenero());
                videojuego.setPlataforma(plataforma != null ? plataforma : videojuego.getPlataforma());
                videojuego.setDesarrollador(desarrollador != null ? desarrollador : videojuego.getDesarrollador());
                videojuego.setPublicador(publicador != null ? publicador : videojuego.getPublicador());
                videojuego.setEsMultijugador(esMultijugador != null ? esMultijugador : videojuego.isEsMultijugador());
                videojuego.setModoHistoria(modoHistoria != null ? modoHistoria : videojuego.isModoHistoria());
                videojuego.setPrecio(precio != null ? precio : videojuego.getPrecio());
                videojuego.setCalificacion(calificacion != null ? calificacion : videojuego.getCalificacion());
                videojuego.setClasificacionEdad(clasificacionEdad != null ? clasificacionEdad : videojuego.getClasificacionEdad());
                videojuego.setFechaLanzamiento(fechaLanzamiento != null ? fechaLanzamiento : videojuego.getFechaLanzamiento());
                videojuego.setNumeroJugadores(numeroJugadores != null ? numeroJugadores : videojuego.getNumeroJugadores());
                videojuego.setDuracionPromedio(duracionPromedio != null ? duracionPromedio : videojuego.getDuracionPromedio());
                videojuego.setMotorGrafico(motorGrafico != null ? motorGrafico : videojuego.getMotorGrafico());
                break; // Salir del bucle una vez que se edita
            }
        }
    }

    // Método para eliminar un videojuego por ID
    public void eliminarVideojuego(int id) {
        videojuegos.removeIf(videojuego -> videojuego.getId() == id); // Remover el videojuego con el ID especificado
    }

    // Método para obtener los detalles del videojuego actual
    public String mostrarDetalles(int id) {
        for (Videojuego videojuego : videojuegos) {
            if (videojuego.getId() == id) {
                return videojuego.mostrarDetalles(); // Retornar detalles del videojuego específico
            }
        }
        return "Videojuego no encontrado"; // Mensaje si no se encuentra el videojuego
    }

    // Método para obtener un videojuego por su ID
    public Videojuego obtenerVideojuegoPorId(int id) {
        for (Videojuego videojuego : videojuegos) {
            if (videojuego.getId() == id) { // Asumiendo que tienes un método getId() en la clase Videojuego
                return videojuego; // Retornar el videojuego encontrado
            }
        }
        return null; // Retornar null si no se encuentra el videojuego
    }

    // Método para establecer el videojuego actual
    public void setVideojuegoActual(Videojuego videojuego) {
        this.videojuegoActual = videojuego;
    }

    // Método para obtener el videojuego actual
    public Videojuego getVideojuegoActual() {
        return this.videojuegoActual;
    }

    public void añadirVideojuegoVacio() {
        Videojuego videojuegoVacio = new Videojuego();  // Usa el constructor vacío
        videojuegos.add(videojuegoVacio);  // Añade el videojuego vacío a la lista
    }

}

