package Entidades;

// Clase abstracta Cuenta
public abstract class Cuenta {
    protected String numeroCuenta;
    protected Cliente titular;
    protected double saldo;

    // Constructor
    public Cuenta(String numeroCuenta, Cliente titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public double consultarSaldo() {
        return saldo;
    }

    // Métodos abstractos
    public abstract void depositar(double monto);
    public abstract void retirar(double monto);

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular=" + titular +
                ", saldo=" + saldo +
                '}';
    }
}
