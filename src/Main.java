import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;
import sistema.ISistema;
import sistema.SistemaArcadia;
import util.Instalador;

public class Main {

    public static void main(String[] args) { configuracion(); }

    /**
     * Método que da cuerpo al sistema y muestra el menú principal.
     */
    private static void configuracion() {
        // se instala el sistema
        ISistema sistema = instalarSistema();
        menuPrincipal(sistema);
    }

    /**
     * Método que instancia el sistema.
     * @return Sistema instanciado.
     */
    private static ISistema instalarSistema() { return new Instalador().inyectarSistema(); }

    /**
     * Método que muestra el menú principal.
     * @param sistema Sistema a utilizar.
     */
    private static void menuPrincipal(ISistema sistema) {
        int opcion = 0;

        while(opcion != 3){
            StdOut.println("--------->Bienvenido a Arcadia<---------");
            StdOut.println("[1] Iniciar Sesión.");
            StdOut.println("[2] Crear Personaje/Registrar.");
            StdOut.println("[3] Salir.");

            StdOut.print("Ingrese su opción: ");
            String opcionStr = StdIn.readString();
            int opcionInt;

            while (true){
                try{
                    opcionInt = Integer.parseInt(opcionStr);
                    if (1 <= opcionInt && opcionInt <= 3){
                        break;
                    }else{
                        StdOut.println("Error, la opción ingresada no existe");
                        StdOut.print("Ingrese su opción nuevamente: ");
                        opcionStr = StdIn.readString();
                    }
                }catch(Exception e){
                    StdOut.println("Error, la opción ingresada no existe");
                    StdOut.print("Ingrese su opción nuevamente: ");
                    opcionStr = StdIn.readString();
                }
            }
            opcion = opcionInt;

            switch (opcion) {
                case 1 -> iniciarSesion(sistema);
                case 2 -> registrarse(sistema);
                case 3 -> StdOut.println("Saliendo del sistema...");
            }
        }
    }

    /**
     * Método que inicia sesión en el sistema.
     * @param sistema Sistema a utilizar.
     */
    private static void iniciarSesion(ISistema sistema) {
        StdOut.println("--------->Iniciar Sesión en Arcadia<---------");
        StdOut.print("Ingrese su nombre de usuario: ");
        String usuario = StdIn.readString();
        StdOut.print("Ingrese su contraseña: ");
        String contrasena = StdIn.readString();

        if (sistema.iniciarSesion(usuario, contrasena)) {
            StdOut.println("Inicio de sesión exitoso.");
            menuUsuario(sistema);
        } else {
            StdOut.println("Inicio de sesión fallido.");
        }
    }

    /**
     * Método que registra un nuevo usuario en el sistema.
     * @param sistema Sistema a utilizar.
     */
    private static void registrarse(ISistema sistema) {
        StdOut.println("--------->Registro en Arcadia<---------");
        StdOut.print("Ingrese su nombre de usuario: ");
        String usuario = StdIn.readString();
        StdOut.print("Ingrese su contraseña: ");
        String contrasena = StdIn.readString();

        String raza;
        while (true) {
            StdOut.println("\nRazas disponibles: Paladin, Mago y Tanque.");

            StdOut.print("Ingrese su raza: ");
            raza = StdIn.readString();

            // Validar input del usuario.
            if (raza.equalsIgnoreCase("Paladin")
                || raza.equalsIgnoreCase("Mago")
                || raza.equalsIgnoreCase("Tanque")) {
                break;
            } else {
                StdOut.println("Raza inválida.");
            }
        }

        if (sistema.crearUsuario(usuario, contrasena, raza)) {
            StdOut.println("Registro exitoso. Inicie sesión.");
        } else {
            StdOut.println("Registro fallido.");
        }
    }

    /**
     * Método que muestra el menú de usuario.
     * @param sistema Sistema a utilizar.
     */
    private static void menuUsuario(ISistema sistema) {
        int opcion = 0;

        while(opcion != 6){
            StdOut.println("--------->Menú de Usuario<---------");
            StdOut.println("[1] Modificar usuario.");
            StdOut.println("[2] Subir de nivel.");
            StdOut.println("[3] Mostrar todos los usuarios.");
            StdOut.println("[4] Mostrar mi usuario.");
            StdOut.println("[5] Eliminar cuenta.");
            StdOut.println("[6] Cerrar Sesión.");

            StdOut.print("Ingrese su opción: ");
            String opcionStr = StdIn.readString();
            int opcionInt;

            while (true){
                try{
                    opcionInt = Integer.parseInt(opcionStr);
                    if (1 <= opcionInt && opcionInt <= 6){
                        break;
                    }else{
                        StdOut.println("Error, la opción ingresada no existe");
                        StdOut.print("Ingrese su opción nuevamente: ");
                        opcionStr = StdIn.readString();
                    }
                }catch(Exception e){
                    StdOut.println("Error, la opción ingresada no existe");
                    StdOut.print("Ingrese su opción nuevamente: ");
                    opcionStr = StdIn.readString();
                }
            }
            opcion = opcionInt;

            switch (opcion) {
                case 1 -> modificarUsuario(sistema);
                case 2 -> subirDeNivel(sistema);
                case 3 -> mostrarUsuarios(sistema);
                case 4 -> mostrarUsuario(sistema);
                case 5 -> {
                    if (eliminarUsuario(sistema)) {
                        opcion = 6;
                    }
                }
                case 6 -> cerrarSesion(sistema);
            }
        }
    }

    /**
     * Método que modifica el usuario.
     * @param sistema Sistema a utilizar.
     */
    private static void modificarUsuario(ISistema sistema) {
        StdOut.println("--------->Modificar Usuario<---------");

        String raza;
        while (true) {
            StdOut.println("\nRazas disponibles: Paladin, Mago y Tanque.");

            StdOut.print("Ingrese su nueva raza: ");
            raza = StdIn.readString();

            // Validar input del usuario.
            if (raza.equalsIgnoreCase("Paladin")
                    || raza.equalsIgnoreCase("Mago")
                    || raza.equalsIgnoreCase("Tanque")) {
                break;
            } else {
                StdOut.println("Raza inválida.");
            }
        }

        if (sistema.modificarUsuario(raza)) {
            StdOut.println("Raza modificada.");
        } else {
            StdOut.println("Raza no modificada.");
        }
    }

    /**
     * Método que sube de nivel al usuario.
     * @param sistema Sistema a utilizar.
     */
    private static void subirDeNivel(ISistema sistema) {
        StdOut.println("--------->Subir de Nivel<---------");
        if (sistema.subirDeNivel()) {
            StdOut.println("Subida de nivel exitosa.");
        } else {
            StdOut.println("Subida de nivel fallida.");
        }
    }

    /**
     * Método que muestra todos los usuarios.
     * @param sistema Sistema a utilizar.
     */
    private static void mostrarUsuarios(ISistema sistema) {
        StdOut.println("--------->Mostrar Usuarios<---------");
        String[] usuarios = sistema.mostrarUsuarios();
        for (String usuario : usuarios) {
            StdOut.println(usuario);
        }
    }

    /**
     * Método que muestra el usuario.
     * @param sistema Sistema a utilizar.
     */
    private static void mostrarUsuario(ISistema sistema) {
        StdOut.println("--------->Mostrar Usuario<---------");
        StdOut.println(sistema.mostrarUsuario());
    }

    /**
     * Método que elimina el usuario.
     * @param sistema Sistema a utilizar.
     * @return Retorna true si el usuario fue eliminado, de lo contrario retorna false.
     */
    private static boolean eliminarUsuario(ISistema sistema) {
        StdOut.println("--------->Eliminar Usuario<---------");

        StdOut.println("¿Está seguro de eliminar su cuenta? (S/N)");
        String respuesta = StdIn.readString();

        if (!respuesta.equalsIgnoreCase("S")) {
            StdOut.println("Operación cancelada.");
            return false;
        }

        if (sistema.eliminarUsuario()) {
            StdOut.println("Usuario eliminado.");
            return true;
        } else {
            StdOut.println("Usuario no eliminado.");
            return false;
        }
    }

    /**
     * Método que cierra la sesión.
     * @param sistema Sistema a utilizar.
     */
    private static void cerrarSesion(ISistema sistema) {
        sistema.cerrarSesion();
        StdOut.println("Sesión cerrada.");
    }

}