package interfaz;

import modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SupermercadoApp {
    private static Inventario inventario = new Inventario();
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static Usuario usuarioActual = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarUsuarios();
        iniciarMenu();
    }

    public static void inicializarUsuarios() {
        listaUsuarios.add(new Usuario(1, "Carlos", "1234"));
        listaUsuarios.add(new Usuario(2, "Maria", "4321"));
    }

    public static void iniciarMenu() {
        while (true) {
            if (usuarioActual == null) {
                autenticarUsuario();
            }

            System.out.println("\n---- Sistema de Gestión de Supermercado ----");
            System.out.println("1. Registrar producto");
            System.out.println("2. Actualizar stock");
            System.out.println("3. Consultar inventario");
            System.out.println("4. Registrar venta");
            System.out.println("5. Cerrar sesión");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    actualizarStock();
                    break;
                case 3:
                    consultarInventario();
                    break;
                case 4:
                    registrarVenta();
                    break;
                case 5:
                    usuarioActual = null;
                    System.out.println("Sesión cerrada.");
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void autenticarUsuario() {
        System.out.println("\n---- Autenticación ----");
        System.out.print("Ingrese su ID de usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id && usuario.autenticar(contrasena)) {
                usuarioActual = usuario;
                System.out.println("Autenticación exitosa. Bienvenido, " + usuarioActual.getNombre() + ".");
                return;
            }
        }
        System.out.println("Autenticación fallida. Intente nuevamente.");
    }

    public static void registrarProducto() {
        System.out.print("Ingrese ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese categoría del producto: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese cantidad en stock: ");
        int stock = scanner.nextInt();

        Producto nuevoProducto = new Producto(id, nombre, categoria, precio, stock);
        inventario.agregarProducto(nuevoProducto);
        System.out.println("Producto registrado correctamente.");
    }

    public static void actualizarStock() {
        System.out.print("Ingrese ID del producto para actualizar stock: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese nueva cantidad en stock: ");
        int nuevaCantidad = scanner.nextInt();

        inventario.actualizarStock(id, nuevaCantidad);
    }

    public static void consultarInventario() {
        inventario.consultarProductos();
    }

    public static void registrarVenta() {
        if (usuarioActual == null) {
            System.out.println("Debes iniciar sesión para registrar una venta.");
            return;
        }

        Venta nuevaVenta = new Venta(usuarioActual);

        while (true) {
            System.out.print("Ingrese el ID del producto que desea vender (o -1 para finalizar): ");
            int idProducto = scanner.nextInt();
            if (idProducto == -1) {
                break;
            }

            Producto producto = inventario.buscarProductoPorId(idProducto);
            if (producto != null) {
                System.out.print("Ingrese la cantidad que desea vender: ");
                int cantidad = scanner.nextInt();

                if (producto.getStock() >= cantidad) {
                    nuevaVenta.agregarProducto(producto, cantidad);
                    producto.setStock(producto.getStock() - cantidad);
                    System.out.println("Producto agregado al carrito.");
                } else {
                    System.out.println("Stock insuficiente.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        }

        // Solo mostrar la venta si se han agregado productos
        if (nuevaVenta.getTotal() > 0) {
            nuevaVenta.mostrarVenta();
        } else {
            System.out.println("No se realizó ninguna venta.");
        }
    }
}
