package BackEnd.ClinicaOdontologica.utils.exeptions;

// Cuando un recurso con la Id especificada no se encuentra.
public class IdNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "No se encontro el recurso con la Id: %d en el modelo de %s";

    public IdNotFoundException(long id, String model) {
        // crea la string con el dato de entrada y lo manda al super
        super(String.format(ERROR_MESSAGE,id,model));
    }
}
