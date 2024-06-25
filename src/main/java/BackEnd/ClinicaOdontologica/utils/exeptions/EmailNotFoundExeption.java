package BackEnd.ClinicaOdontologica.utils.exeptions;

// Cuando un recurso  El Email especificado no se encuentra.
public class EmailNotFoundExeption extends RuntimeException{

        private static final String ERROR_MESSAGE = "No se encontro el registro con el Email: %s en el modelo de %s";

    public EmailNotFoundExeption(String email, String model) {
        super(String.format(ERROR_MESSAGE,email,model));
    }
}


