package Presentacion.dialogs.registro;

import DAO.CitaDAO;
import DAO.DoctorDAO;
import DAO.PacienteDAO;
import DTO.CitaDTO;
import Dominios.DoctorDominio;
import Dominios.PacienteDominio;
import Presentacion.paneles.PnlCitas;
import Presentacion.styles.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DlgRegistrarCita extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    String doctor, dia, mes, anio, hora, paciente, motivo;
    PnlCitas pnlCitas;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Agendar nueva cita", 32);

    CustomLabel lblDoctor = new CustomLabel("Doctor", 24);
    CustomLabel lblFecha = new CustomLabel("Fecha", 24);
    CustomLabel lblHora = new CustomLabel("Hora", 24);
    CustomLabel lblPaciente = new CustomLabel("Paciente", 24);
    CustomLabel lblMotivo = new CustomLabel("Motivo", 24);


    //----------LÓGICA AQUI----------
    //Contenido de las comboboxes de ejemplo: cambiar por arrays con la info correcta de las opciones disponibles en la base
    String[] doctores = {"Dr.Pancracio López", "Dra. Juliana Pérez", "Dr. Simi"};
    String[] fechaDias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] fechaMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] fechaAnios = {"2025", "2026"};
    String[] horas = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
    String[] pacientes = {"Fulanito D´tal", "Juan Pérez", "Hermenegildo García"};
    //----------FIN DE LÓGICA----------


    //Textfields y comboboxes
    CustomComboBox cboxDoctores = new CustomComboBox(doctores);
    CustomComboBox cboxFechaDias = new CustomComboBox(fechaDias);
    CustomComboBox cboxFechaMeses = new CustomComboBox(fechaMeses);
    CustomComboBox cboxFechaAnios = new CustomComboBox(fechaAnios);
    CustomComboBox cboxHoras = new CustomComboBox(horas);
    CustomComboBox cboxPacientes = new CustomComboBox(pacientes);
    TxtFieldPh txtfldMotivo = new TxtFieldPh("Motivo", true, 200, 40, 24);

    //Botones
    CustomButton btnAgendarCita = new CustomButton("Agendar cita");



    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorh1 = new Espaciador(10, 10);
    
   


    public DlgRegistrarCita(Frame parent, PnlCitas pnlCitas) {

        //Setup del dialog
        super(parent, "Agendar cita");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        this.pnlCitas = pnlCitas;


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Contenido
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        contenido.add(lblDoctor);
        contenido.add(cboxDoctores);

        contenido.add(lblFecha);
        contenido.add(cboxFechaDias);
        contenido.add(cboxFechaMeses);
        contenido.add(cboxFechaAnios);

        contenido.add(lblHora);
        contenido.add(cboxHoras);

        contenido.add(lblPaciente);
        contenido.add(cboxPacientes);

        contenido.add(lblMotivo);
        contenido.add(txtfldMotivo);
      
        try {
            DoctorDAO doctorDAO = new DoctorDAO();
            List<Dominios.DoctorDominio> listaDocs = doctorDAO.readall();
            cboxDoctores.removeAllItems();
            for (Dominios.DoctorDominio dd : listaDocs) {
                cboxDoctores.addItem(dd);
            }

            PacienteDAO pacienteDAO = new PacienteDAO();
            List<Dominios.PacienteDominio> listaPacs = pacienteDAO.readall();
            cboxPacientes.removeAllItems();
            for (Dominios.PacienteDominio pd : listaPacs) {
                cboxPacientes.addItem(pd);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando doctores/pacientes: " + ex.getMessage());
            ex.printStackTrace();
        }


        //Botones
        btnAgendarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                agendarCita();
            }
        });
        contenido.add(btnAgendarCita);



        add(contenido);

        //setVisible(true);

    }


    //----------LÓGICA AQUÍ----------

    public void agendarCita() {
 
        try {
        // 1) Obtener objetos seleccionados (ahora los combobox deben contener objetos Dominio, no solo strings)
        DoctorDominio docSel = (DoctorDominio) cboxDoctores.getSelectedItem();
        PacienteDominio pacSel = (PacienteDominio) cboxPacientes.getSelectedItem();

        if (docSel == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un doctor.");
            return;
        }
        if (pacSel == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente.");
            return;
        }

        // 2) Extraer fecha/hora/motivo
        String dia = cboxFechaDias.getSelectedItem().toString();
        String mesNombre = cboxFechaMeses.getSelectedItem().toString();
        String anio = cboxFechaAnios.getSelectedItem().toString();
        String horaSeleccionada = cboxHoras.getSelectedItem().toString(); // ✅ corregido: antes tomaba cboxDoctores
        String motivoLocal = txtfldMotivo.getText().trim();

        if (motivoLocal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el motivo de la cita.");
            return;
        }

        // 3) Convertir mes en texto a número (1-12)
        int mesNumero = mapMesEspANumero(mesNombre); // método definido más abajo

        if (mesNumero <= 0) {
            JOptionPane.showMessageDialog(this, "Mes invalido: " + mesNombre);
            return;
        }

        // 4) Construir LocalDate yyyy-MM-dd
        LocalDate fecha;
        try {
            int diaInt = Integer.parseInt(dia);
            int anioInt = Integer.parseInt(anio);
            fecha = LocalDate.of(anioInt, mesNumero, diaInt);
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida: " + ex.getMessage());
            return;
        }

            // 5) Preparar DTO para insertar en BD
            CitaDTO nueva = new CitaDTO(
                    motivoLocal,
                    Date.valueOf(fecha),
                    Dominios.ENUM.Estado.PROGRAMADA,
                    docSel.getId_doctor(),
                    pacSel.getId_paciente()
            );

        nueva.setEstado(Dominios.ENUM.Estado.PROGRAMADA);

        // 6) Insertar usando DAO (usa tu implementación ya existente)
        CitaDAO citaDAO = new CitaDAO();
        boolean ok = citaDAO.insert(nueva);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la cita en la base de datos.");
            return;
        }

        // 7) Refrescar la vista de citas y cerrar diálogo
        if (pnlCitas != null) {
            pnlCitas.refresh();
        }
        JOptionPane.showMessageDialog(this, "Cita agendada correctamente para " + fecha.toString() + " " + horaSeleccionada);
        this.dispose();

    } catch (ClassCastException cce) {
        // esto significa que los combobox no estaban cargados con objetos Dominio
        JOptionPane.showMessageDialog(this, "Error interno: combobox no contiene objetos esperados. " +
                "Asegurate de que los combos alimenten Dominios (DoctorDominio / PacienteDominio).");
        cce.printStackTrace();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al agendar: " + ex.getMessage());
        ex.printStackTrace();
    }
    }
    
    //onvierte mes español -> número
private int mapMesEspANumero(String mes) {
    return switch (mes.toLowerCase()) {
        case "enero" -> 1;
        case "febrero" -> 2;
        case "marzo" -> 3;
        case "abril" -> 4;
        case "mayo" -> 5;
        case "junio" -> 6;
        case "julio" -> 7;
        case "agosto" -> 8;
        case "septiembre" -> 9;
        case "octubre" -> 10;
        case "noviembre" -> 11;
        case "diciembre" -> 12;
        default -> -1;
    };
}
    

    //----------FIN DE LÓGICA----------

}
