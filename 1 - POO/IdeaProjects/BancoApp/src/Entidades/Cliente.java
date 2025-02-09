package Entidades;

import Interface.InformacionMostrable;
import java.util.ArrayList;

public class Cliente extends Persona implements InformacionMostrable { // Hereda de Persona
    private String direccion;
    private String telefono;
    private String email;
    private ArrayList<CuentaBancaria> cuentas;

    public Cliente(String nombre, String apellido, String dni, String direccion, String telefono, String email) {
        super(nombre, apellido, dni); // Llama al constructor de Persona
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    // Métodos getter para acceder a los datos
    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // Método para actualizar datos
    public void actualizarDatos(String nuevoTelefono, String nuevaDireccion, String nuevoEmail) {
        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            this.telefono = nuevoTelefono;
        }
        if (nuevaDireccion != null && !nuevaDireccion.isEmpty()) {
            this.direccion = nuevaDireccion;
        }
        if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
            this.email = nuevoEmail;
        }
        System.out.println("Datos actualizados correctamente.");
        System.out.println("Teléfono: " + this.telefono);
        System.out.println("Dirección: " + this.direccion);
        System.out.println("Email: " + this.email);
    }

    // Método sobrescrito de Persona
    @Override
    public void mostrarInformacion() {
        System.out.println("-- Cliente: " + getNombre() + " " + getApellido());
        System.out.println("-- DNI: " + getDni());
        System.out.println("-- Dirección: " + direccion);
        System.out.println("-- Teléfono: " + telefono);
        System.out.println("-- Email: " + email);
        System.out.println("-- Número de cuentas: " + cuentas.size());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + getNombre() + '\'' + // Utiliza los métodos getter de Persona
                ", apellido='" + getApellido() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
