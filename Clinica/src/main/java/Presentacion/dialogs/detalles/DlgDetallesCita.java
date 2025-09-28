package Clinica.src.main.java.Presentacion.dialogs.detalles;

import Presentacion.paneles.PnlCitas;
import Presentacion.paneles.elementos.PnlElementoCita;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesCita extends JDialog {


    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombreCita = "C-002";
    String nombreDoctor = "Dr.Pancracio Lopez";
    String nombrePaciente = "Fulanito D´tal";
    String fechaHora = "dd/mm/aaaa 00:00hrs";
    String estado = "En curso";
    String motivo = "lele pancha";
    //----------FIN DEL PLACEHOLDER-----------


    boolean testeoColor = false;
    PnlCitas pnlCitas;
    PnlElementoCita pnlElementoCita;
    Style style = new Style();

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Cita " + nombreCita, 32);

    CustomLabel lblColMedico = new CustomLabel("        Médico      ", 24);
    CustomLabel lblMedico = new CustomLabel(nombreDoctor, style.letraSize);

    CustomLabel lblColPaciente = new CustomLabel("      Paciente        ", 24);
    CustomLabel lblPaciente = new CustomLabel(nombrePaciente, style.letraSize);

    CustomLabel lblColFechaHora = new CustomLabel("     Fecha y hora        ", 24);
    CustomLabel lblFechaHora = new CustomLabel(fechaHora, style.letraSize);

    CustomLabel lblColEstado = new CustomLabel("        Estado      ", 24);
    CustomLabel lblEstado = new CustomLabel(estado, style.letraSize);

    CustomLabel lblColMotivo = new CustomLabel("        Motivo      ", 24);
    CustomLabel lblMotivo = new CustomLabel(motivo, style.letraSize);


    //Boton
    CustomButton btnCancelarCita = new CustomButton("Marcar como cancelada", 1, 200, 30);
    CustomButton btnAtenderCita = new CustomButton("Marcar como en curso", 1, 200, 30);
    CustomButton btnCompletarCita = new CustomButton("Marcar como completada", 1, 200, 30);


    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);
    ContainerPanel botones = new ContainerPanel(style.dialogX, 50, Color.PINK, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);

    Espaciador espaciadorh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo cita

    //public DlgDetallesCita(Frame parent, PnlCitas pnlCitas, Cita cita) {
    public DlgDetallesCita(Frame parent, PnlCitas pnlCitas, PnlElementoCita pnlElementoCita) {

        //Setup del dialog
        super(parent, "Detalles de cita");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlCitas = pnlCitas;
        this.pnlElementoCita = pnlElementoCita;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombreCita = cita.getNombreCita();
        nombre = cita.getNombreDoctor()";
        nombre = cita.getNombrePaciente();;
        cita = cita.getFechaHora();
        estado = cita.getEstado();
        descripcion = cita.getMotivo();

        */
        //----------FIN DE LÓGICA----------


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Título
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        //Info de la cita
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(lblColMedico);
        columnas.add(lblColPaciente);
        columnas.add(lblColFechaHora);
        columnas.add(lblColEstado);
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        informacion.add(lblMedico);
        informacion.add(lblPaciente);
        informacion.add(lblFechaHora);
        informacion.add(lblEstado);
        contenido.add(informacion);

        contenido.add(espaciadorv3);
        contenido.add(lblColMotivo);
        contenido.add(lblMotivo);

        contenido.add(espaciadorv4);

        //-----Lógica???
        //Tratamientos
        //insertar cosa de los tratamientos derivados de esta cita

        //Botones
        btnCancelarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelarCita();
            }
        });
        btnAtenderCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atenderCita();
            }
        });
        btnCompletarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                completarCita();
            }
        });

        botones.add(btnCancelarCita);
        botones.add(btnAtenderCita);
        botones.add(btnCompletarCita);
        contenido.add(botones);


        add(contenido);

    }


    //----------LÓGICA AQUÍ----------

    public void cancelarCita() {

        //hacer cosa mágica para que se elimine la cita actual
        System.out.println("Haz de cuenta que se canceló la cita");

        pnlCitas.refresh();
        pnlElementoCita.refresh();
        this.dispose();

    }

    public void atenderCita() {

        //hacer cosa mágica para que se marque como en curso
        System.out.println("Haz de cuenta que la cita ahora está en curso");

        pnlCitas.refresh();
        pnlElementoCita.refresh();
        this.dispose();

    }

    public void completarCita() {

        //hacer cosa mágica para marcar como completada la cita
        System.out.println("Haz de cuenta que se completó la cita");

        pnlCitas.refresh();
        pnlElementoCita.refresh();
        this.dispose();

    }

    //----------FIN DE LÓGICA----------

}
