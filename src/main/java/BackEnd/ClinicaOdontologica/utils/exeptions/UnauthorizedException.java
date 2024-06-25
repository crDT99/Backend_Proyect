package BackEnd.ClinicaOdontologica.utils.exeptions;

public class UnauthorizedException extends RuntimeException {
    private static final String ERROR_MESSAGE = "No se encontro el recurso con la Id solicitada en el modelo de %s";

}
