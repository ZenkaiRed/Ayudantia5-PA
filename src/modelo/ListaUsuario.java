package modelo;

public class ListaUsuario {

    private Usuario[] listaUsuario;
    private int cantidadActual;
    private int cantidadMaxima;

    /**
     * Constructor de la clase ListaUsuario.
     * @param cantidadMaxima Cantidad máxima de usuarios que se pueden almacenar.
     */
    public ListaUsuario(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.listaUsuario = new Usuario[cantidadMaxima];
        this.cantidadActual = 0;
    }

    /**
     * Método que permite agregar un usuario a la lista.
     * @param usuario Usuario a agregar.
     * @return True si se agrega correctamente, false en caso contrario.
     */
    public boolean agregarUsuario(Usuario usuario) {

        // Verifica si hay espacio para agregar un nuevo usuario.
        if (this.cantidadActual < this.cantidadMaxima) {

            // Agrega el usuario a la lista.
            this.listaUsuario[this.cantidadActual] = usuario;
            this.cantidadActual++;
            return true;
        }

        // Retorna falso debido a que no hay más espacio.
        return false;
    }

    /**
     * Método que permite obtener un usuario de la lista.
     * @param nombre Nombre del usuario a buscar.
     * @return Usuario si se encuentra, null en caso contrario.
     */
    public Usuario obtenerUsuario(String nombre) {

        for (int i = 0; i < this.cantidadActual; i++) {
            Usuario usuario =  this.listaUsuario[i];

            // Verifica si el usuario actual es el que se busca.
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }

        // Retorna null si no se encuentra el usuario.
        return null;
    }

    /**
     * Método que permite obtener la lista de usuarios.
     * @return Arreglo con los usuarios.
     */
    public Usuario[] obtenerUsuarios() { return this.listaUsuario; }

    /**
     * Método que permite buscar un usuario en la lista y extraer la posición.
     * @param nombre Nombre del usuario a buscar.
     * @return Posición del usuario en la lista, -1 si no se encuentra.
     */
    public int buscarUsuario(String nombre) {

        // Recorre la lista de usuarios.
        for (int i = 0; i < this.cantidadActual; i++) {

            // Verifica si el usuario actual es el que se busca.
            if (this.listaUsuario[i].getNombre().equals(nombre)) {

                // Retorna la posición del usuario.
                return i;
            }
        }

        // Retorna -1 si no se encuentra el usuario.
        return -1;
    }

    /**
     * Método que permite eliminar un usuario de la lista.
     * @param posicion Posición del usuario a eliminar.
     * @return True si se elimina correctamente, false en caso contrario.
     */
    public boolean eliminarUsuario(int posicion) {

        // Verifica si la posición es válida.
        if (posicion >= 0 && posicion < this.cantidadActual) {

            // Elimina el usuario de la lista.
            for (int i = posicion; i < this.cantidadActual - 1; i++) {
                this.listaUsuario[i] = this.listaUsuario[i + 1];
            }

            // Disminuye la cantidad de usuarios.
            this.cantidadActual--;
            return true;
        }

        // Retorna falso si la posición no es válida.
        return false;

    }

    public int getCantidadActual() { return cantidadActual; }
}
