package sistema;

import modelo.ListaUsuario;
import modelo.Usuario;

public class SistemaArcadia implements ISistema {

    private ListaUsuario usuarios;
    private Usuario usuarioLogueado;

    public SistemaArcadia() {
        // Sobredimensiono la lista de usuarios para evitar problemas de espacio.
        this.usuarios = new ListaUsuario(999);
        this.usuarioLogueado = null;
    }

    /**
     * Inicia sesión en el sistema.
     *
     * @param usuario    Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return true si el usuario y contraseña son correctos, false en caso contrario.
     */
    @Override
    public boolean iniciarSesion(String usuario, String contrasena) {

        // Paso 1: Si ya hay un usuario iniciado, no se puede iniciar sesión.
        if (this.usuarioLogueado != null) {
            return false;
        }

        // Paso 2: Busco el usuario en la lista.
        Usuario usuarioBuscado = this.usuarios.obtenerUsuario(usuario);

        // Paso 3: Si el usuario no existe, no se puede iniciar sesión.
        if (usuarioBuscado == null) {
            return false;
        }

        // Paso 4: Si la contraseña no coincide, no se puede iniciar sesión.
        if (!usuarioBuscado.getContrasenia().equals(contrasena)) {
            return false;
        }

        // Paso 5: Inicio sesión.
        this.usuarioLogueado = usuarioBuscado;
        return true;

    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario    Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param raza       Raza del usuario.
     * @return true si el usuario fue creado exitosamente, false en caso contrario.
     */
    @Override
    public boolean crearUsuario(String usuario, String contrasena, String raza) {

        // Paso 1: Si ya hay un usuario iniciado, no se puede crear un nuevo usuario.
        if (this.usuarioLogueado != null) { return false; }

        // Paso 2: Si el usuario ya existe, no se puede crear un nuevo usuario.
        if (this.usuarios.obtenerUsuario(usuario) != null) { return false; }

        // Paso 3: Verificar que la raza entregada sea válida.
        if (!raza.equalsIgnoreCase("Paladin")
            && !raza.equalsIgnoreCase("Mago")
            && !raza.equalsIgnoreCase("Tanque")) {
            return false;
        }

        // Paso 4: Creo el usuario.
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, raza);

        // Paso 5: Agrego el usuario a la lista y retorno el resultado de este método.
        return this.usuarios.agregarUsuario(nuevoUsuario);

    }

    /**
     * Muestra todos los usuarios del sistema.
     *
     * @return Arreglo de strings con los nombres de usuario.
     */
    @Override
    public String[] mostrarUsuarios() {

        // Paso 1: Verificar que haya un usuario logueado.
        if (this.usuarioLogueado == null) { return null; }

        // Paso 2: Obtener los usuarios.
        Usuario[] usuarios = this.usuarios.obtenerUsuarios();

        // Paso 3: Crear un arreglo de strings con los datos de los usuarios.
        String[] datosUsuarios = new String[this.usuarios.getCantidadActual()];

        for (int i = 0; i < datosUsuarios.length; i++) {
            datosUsuarios[i] = usuarios[i].toString();
        }

        // Paso 4: Retornar el arreglo de strings.
        return datosUsuarios;
    }

    /**
     * Muestra un usuario en específico.
     *
     * @return Información del usuario.
     */
    @Override
    public String mostrarUsuario() { return this.usuarioLogueado.toString(); }

    /**
     * Elimina un usuario del sistema.
     *
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     */
    @Override
    public boolean eliminarUsuario() {

        // Paso 1: Verificar que haya un usuario logueado.
        if (this.usuarioLogueado == null) { return false; }

        // Paso 2: Buscar la posición del usuario logueado.
        int posicionUsuario = this.usuarios.buscarUsuario(this.usuarioLogueado.getNombre());

        // Paso 3: Desloguear al usuario y eliminar el usuario de la lista.

        this.usuarioLogueado = null;

        return this.usuarios.eliminarUsuario(posicionUsuario);
    }

    /**
     * Modifica la raza de un usuario.
     *
     * @param raza Nueva raza del usuario.
     * @return true si la raza fue modificada exitosamente, false en caso contrario.
     */
    @Override
    public boolean modificarUsuario(String raza) {

        // Paso 1: Verificar que haya un usuario logueado.
        if (this.usuarioLogueado == null) { return false; }

        // Paso 2: Validar la raza entregada.
        if (!raza.equalsIgnoreCase("Paladin")
            && !raza.equalsIgnoreCase("Mago")
            && !raza.equalsIgnoreCase("Tanque")) {
            return false;
        }

        // Paso 3: Modificar la raza del usuario.
        this.usuarioLogueado.setRaza(raza);
        return true;
    }

    /**
     * Sube de nivel al usuario.
     *
     * @return true si el usuario subió de nivel, false en caso contrario.
     */
    @Override
    public boolean subirDeNivel() {

        // Paso 1: Verificar que haya un usuario logueado.
        if (this.usuarioLogueado == null) { return false; }

        // Paso 2: Verificar que el usuario no sea nivel 99.
        if (this.usuarioLogueado.getNivel() >= 99) { return false; }

        // Paso 3: Subir de nivel al usuario.
        this.usuarioLogueado.setNivel(this.usuarioLogueado.getNivel() + 1);

        // Paso 4: Subir demás estadísticas del usuario.
        switch (this.usuarioLogueado.getRaza()) {
            case "PALADIN" ->
                this.subirEstadisticasPaladin();
            case "MAGO" ->
                this.subirEstadisticasMago();
            case "TANQUE" ->
                this.subirEstadisticasTanque();
        }

        return true;
    }

    /**
     * Cierra la sesión del usuario.
     */
    @Override
    public void cerrarSesion() { this.usuarioLogueado = null; }

    /**
     * Sube las estadísticas del usuario si es de tipo Paladin.
     */
    private void subirEstadisticasPaladin() {

        this.usuarioLogueado.setAtaque(this.usuarioLogueado.getAtaque() + 3);
        this.usuarioLogueado.setDefensa(this.usuarioLogueado.getDefensa() + 1);
        this.usuarioLogueado.setSalud(this.usuarioLogueado.getSalud() + 2);

    }

    /**
     * Sube las estadísticas del usuario si es de tipo Mago.
     */
    private void subirEstadisticasMago() {

        this.usuarioLogueado.setAtaque(this.usuarioLogueado.getAtaque() + 2);
        this.usuarioLogueado.setDefensa(this.usuarioLogueado.getDefensa() + 2);
        this.usuarioLogueado.setSalud(this.usuarioLogueado.getSalud() + 1);

    }

    /**
     * Sube las estadísticas del usuario si es de tipo Tanque.
     */
    private void subirEstadisticasTanque() {

        this.usuarioLogueado.setAtaque(this.usuarioLogueado.getAtaque() + 1);
        this.usuarioLogueado.setDefensa(this.usuarioLogueado.getDefensa() + 3);
        this.usuarioLogueado.setSalud(this.usuarioLogueado.getSalud() + 3);

    }
}
