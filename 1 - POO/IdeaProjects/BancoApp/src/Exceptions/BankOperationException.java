package Exceptions;

// Excepción personalizada para operaciones bancarias
public class BankOperationException extends Exception {
    public BankOperationException(String message) {
        super(message); // Llama al constructor de la clase Exception
    }
}
