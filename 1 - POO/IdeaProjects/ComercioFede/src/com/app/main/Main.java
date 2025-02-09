package com.app.main;

import com.app.gestor.GestorReservas;

public class Main {
    public static void main(String[] args) {
    	// Creación e Inicio de Gestor de Reservas
        GestorReservas gestor = new GestorReservas();
        gestor.iniciar();
    }
}
