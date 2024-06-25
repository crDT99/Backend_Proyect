package BackEnd.ClinicaOdontologica.utils.InitVariables;


import BackEnd.ClinicaOdontologica.entity.Domicilio;
import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.entity.Turno;
import BackEnd.ClinicaOdontologica.service.OdontologoService;
import BackEnd.ClinicaOdontologica.service.PacienteService;
import BackEnd.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
    @Component
    public class RegistrosDefault implements ApplicationRunner {
        //  -----------  Inyeccion de servicios -----------
        @Autowired
        PacienteService pacienteService;
        @Autowired
        OdontologoService odontologoService;
        @Autowired
        TurnoService turnoService;

        //  -----------  Insercion de datos de Ejemplo -----------
        @Override
        public void run(ApplicationArguments args) throws Exception {

            Domicilio domExample = Domicilio.builder()
                    .calle("Av Siempreviva")
                    .numero(742)
                    .localidad("Springfield")
                    .provincia("USA")
                    .build();
            Domicilio domExample2 = Domicilio.builder()
                    .calle("Av Siempreviva")
                    .numero(744)
                    .localidad("Springfield")
                    .provincia("USA")
                    .build();


            //***** Pacientes *****
            Paciente paciente0 =  Paciente.builder()
                    .nombre("Homero")
                    .apellido("Simpson")
                    .cedula("34542233")
                    .fechaIngreso(LocalDate.of(2024, 1, 13))
                    .domicilio(domExample)
                    .email("homero@simpson.com")
                    .build();
            pacienteService.guardarPaciente(paciente0);

            Paciente paciente1 =  Paciente.builder()
                    .nombre("Ned")
                    .apellido("Flanders")
                    .cedula("31123226")
                    .fechaIngreso(LocalDate.of(2022, 2, 16))
                    .domicilio(domExample2)
                    .email("ned@flanders.com")
                    .build();
            pacienteService.guardarPaciente(paciente1);

            //***** Odontologos *****
            Odontologo odontologo0 = Odontologo.builder()
                    .matricula("OD07")
                    .nombre("Cristiano")
                    .apellido("Ronaldo")
                    .build();
            odontologoService.guardarOdontologo(odontologo0);

            Odontologo odontologo1 = Odontologo.builder()
                    .matricula("OD10")
                    .nombre("Leonel")
                    .apellido("Messi")
                    .build();
            odontologoService.guardarOdontologo(odontologo1);

            //***** Turnos *****
            Turno turno0 = Turno.builder()
                    .fecha(LocalDate.of(2023, 7, 13))
                    .paciente(paciente0)
                    .odontologo(odontologo0)
                    .build();
            turnoService.guardarTurno(turno0);

            Turno turno1 = Turno.builder()
                    .fecha(LocalDate.of(2024, 7, 20))
                    .paciente(paciente1)
                    .odontologo(odontologo1)
                    .build();
            turnoService.guardarTurno(turno1);

        }



    }

