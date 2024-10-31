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
        videojuegos = new ArrayList<>(); // Inicializamos la lista
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
}

