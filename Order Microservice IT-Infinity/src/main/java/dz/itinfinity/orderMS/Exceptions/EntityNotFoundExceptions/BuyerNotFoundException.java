package dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions;

public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException(String message) {
        super(message);
    }
}
