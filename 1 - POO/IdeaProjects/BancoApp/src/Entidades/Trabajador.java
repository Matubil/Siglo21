package Entidades;

import Interface.InformacionMostrable;

public class Trabajador extends Persona implements InformacionMostrable {
    private String sector;
    private String puesto;

    public Trabajador(String nombre, String apellido, String dni, String sector, String puesto) {
        super(nombre, apellido, dni); // Llama al constructor de Persona
        this.sector = sector;
        this.puesto = puesto;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    // Método sobrescrito de Persona
    @Override
    public void mostrarInformacion() {
        System.out.println("-- Trabajador: " + getNombre() + " " + getApellido());
        System.out.println("-- DNI: " + getDni());
        System.out.println("-- Sector: " + sector);
        System.out.println("-- Puesto: " + puesto);
    }

    @Override
    public String toString() {
        return "Trabajador: " + getNombre() + " " + getApellido() +
                ", DNI: " + getDni() + ", Sector: " + sector + ", Puesto: " + puesto;
    }
}
