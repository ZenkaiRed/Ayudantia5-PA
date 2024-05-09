package modelo;

import java.util.Random;

public class Usuario {

    private final String nombre;
    private final String contrasenia;
    private String raza;
    private int nivel;
    private int ataque;
    private int defensa;
    private int salud;

    /**
     * Constructor de la clase Usuario.
     * @param nombre Nombre del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param raza Raza del usuario.
     */
    public Usuario(String nombre, String contrasenia, String raza) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.raza = raza.toUpperCase();
        this.nivel = 1;

        Random random = new Random();
        this.ataque = random.nextInt(10) + 1; // Se suma 1 para que el número esté entre 1 y 10
        this.defensa = random.nextInt(10) + 1;
        this.salud = random.nextInt(10) + 1;
    }

    /**
     * Método que permite enviar la representación en String de un objeto de la clase Usuario.
     * @return String con la información del objeto.
     */
    public String toString(){
        return "Nombre: " + this.nombre + "\n" +
                "Raza: " + this.raza + "\n" +
                "Nivel: " + this.nivel + "\n" +
                "Ataque: " + this.ataque + "\n" +
                "Defensa: " + this.defensa + "\n" +
                "Salud: " + this.salud + "\n";
    }

    public String getNombre() { return nombre; }

    public String getContrasenia() { return contrasenia; }

    public String getRaza() { return raza; }

    public void setRaza(String raza) { this.raza = raza.toUpperCase(); }

    public int getNivel() { return nivel; }

    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getAtaque() { return ataque; }

    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }

    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getSalud() { return salud; }

    public void setSalud(int salud) { this.salud = salud; }
}
