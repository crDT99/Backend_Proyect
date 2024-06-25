package BackEnd.ClinicaOdontologica.utils.exeptions;

//Para manejar situaciones donde la solicitud del cliente es inválida
public class BadRequestException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Peticion de cliente invalida";

    public BadRequestException() {
        super(ERROR_MESSAGE);
    }
}
