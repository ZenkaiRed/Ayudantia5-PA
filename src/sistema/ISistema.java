package sistema;

public interface ISistema {

    /**
     * Inicia sesión en el sistema.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return true si el usuario y contraseña son correctos, false en caso contrario.
     */
    boolean iniciarSesion(String usuario, String contrasena);
    /**
     * Crea un nuevo usuario en el sistema.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param raza Raza del usuario.
     * @return true si el usuario fue creado exitosamente, false en caso contrario.
     */
    boolean crearUsuario(String usuario, String contrasena, String raza);
    /**
     * Muestra todos los usuarios del sistema.
     * @return Arreglo de strings con los nombres de usuario.
     */
    String[] mostrarUsuarios();
    /**
     * Muestra un usuario en específico.
     * @return Información del usuario.
     */
    String mostrarUsuario();
    /**
     * Elimina un usuario del sistema.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     */
    boolean eliminarUsuario();
    /**
     * Modifica la raza de un usuario.
     * @param raza Nueva raza del usuario.
     * @return true si la raza fue modificada exitosamente, false en caso contrario.
     */
    boolean modificarUsuario(String raza);
    /**
     * Sube de nivel al usuario.
     * @return true si el usuario subió de nivel, false en caso contrario.
     */
    boolean subirDeNivel();
    /**
     * Cierra la sesión del usuario.
     */
    void cerrarSesion();

}
