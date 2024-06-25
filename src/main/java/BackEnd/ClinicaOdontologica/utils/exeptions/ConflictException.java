package BackEnd.ClinicaOdontologica.utils.exeptions;

//conflicto al realizar una operación
public class ConflictException extends RuntimeException{
    private static final String ERROR_MESSAGE = "el recurso solicitado presenta conflicto, por favor revise que no este repetido";

    public ConflictException() {
        super(ERROR_MESSAGE);
    }
}
