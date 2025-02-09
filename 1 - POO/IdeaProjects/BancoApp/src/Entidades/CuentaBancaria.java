package Entidades;

import java.util.ArrayList;

public class CuentaBancaria extends Cuenta {
    private ArrayList<Transaccion> transacciones; // Para almacenar transacciones

    public CuentaBancaria(String numeroCuenta, Cliente titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial); // Llamar al constructor de la clase base
        this.transacciones = new ArrayList<>();
    }

    public String obtenerInfoCuenta() {
        return "Número de cuenta: " + numeroCuenta + ", Saldo: " + saldo;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
        transacciones.add(new Transaccion("Depósito de $", monto)); // Registrar transacción
    }

    @Override
    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            transacciones.add(new Transaccion("Retiro de $", monto)); // Registrar transacción
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones; // Retornar transacciones
    }

    @Override
    public String toString() {
        return super.toString(); // Llama al toString de la clase base
    }
}
