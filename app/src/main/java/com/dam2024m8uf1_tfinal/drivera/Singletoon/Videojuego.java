package com.dam2024m8uf1_tfinal.drivera.Singletoon;

import java.util.Calendar;

public class Videojuego {
    private static int contadorId = 0; // Contador estático para asignar IDs únicos
    private final int id; // ID único para cada videojuego
    private String titulo;
    private String genero;
    private String plataforma;
    private String desarrollador;
    private String publicador;
    private boolean esMultijugador;
    public boolean modoHistoria;
    private double precio;
    private float calificacion;
    private String clasificacionEdad;
    private Calendar fechaLanzamiento;
    private int numeroJugadores;
    private int duracionPromedio;
    private String motorGrafico;

    // Constructor
    public Videojuego(String titulo, String genero, String plataforma, String desarrollador, String publicador,
                      boolean esMultijugador, boolean modoHistoria, double precio, float calificacion,
                      String clasificacionEdad, Calendar fechaLanzamiento, int numeroJugadores,
                      int duracionPromedio, String motorGrafico) {
        this.id = ++contadorId; // Asigna un nuevo ID único
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.desarrollador = desarrollador;
        this.publicador = publicador;
        this.esMultijugador = esMultijugador;
        this.modoHistoria = modoHistoria;
        this.precio = precio;
        this.calificacion = calificacion;
        this.clasificacionEdad = clasificacionEdad;
        this.fechaLanzamiento = fechaLanzamiento;
        this.numeroJugadores = numeroJugadores;
        this.duracionPromedio = duracionPromedio;
        this.motorGrafico = motorGrafico;
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
    }

    public boolean isEsMultijugador() {
        return esMultijugador;
    }

    public void setEsMultijugador(boolean esMultijugador) {
        this.esMultijugador = esMultijugador;
    }

    public boolean isModoHistoria() {
        return modoHistoria;
    }

    public void setModoHistoria(boolean modoHistoria) {
        this.modoHistoria = modoHistoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public Calendar getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Calendar fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public int getDuracionPromedio() {
        return duracionPromedio;
    }

    public void setDuracionPromedio(int duracionPromedio) {
        this.duracionPromedio = duracionPromedio;
    }

    public String getMotorGrafico() {
        return motorGrafico;
    }

    public void setMotorGrafico(String motorGrafico) {
        this.motorGrafico = motorGrafico;
    }

    // Método para mostrar detalles del videojuego
    public String mostrarDetalles() {
        return "ID: " + this.id + "\n" +
                "Título: " + this.titulo + "\n" +
                "Género: " + this.genero + "\n" +
                "Plataforma: " + this.plataforma + "\n" +
                "Desarrollador: " + this.desarrollador + "\n" +
                "Publicador: " + this.publicador + "\n" +
                "Es Multijugador: " + this.esMultijugador + "\n" +
                "Modo Historia: " + this.modoHistoria + "\n" +
                "Precio: $" + this.precio + "\n" +
                "Calificación: " + this.calificacion + " / 5\n" +
                "Clasificación de Edad: " + this.clasificacionEdad + "\n" +
                "Fecha de Lanzamiento: " + (this.fechaLanzamiento != null ? this.fechaLanzamiento.getTime() : "No disponible") + "\n" +
                "Número de Jugadores: " + this.numeroJugadores + "\n" +
                "Duración Promedio: " + this.duracionPromedio + " horas\n" +
                "Motor Gráfico: " + this.motorGrafico;
    }
}

