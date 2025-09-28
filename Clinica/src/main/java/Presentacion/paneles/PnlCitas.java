package Clinica.src.main.java.Presentacion.paneles;

import Presentacion.dialogs.registro.DlgRegistrarCita;
import Presentacion.paneles.elementos.PnlElementoCita;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlCitas extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;
    PnlCitas pnlCitas = this;

    //Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Citas", 24);

    //Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.ORANGE, testeoColor);
    CustomLabel lblMedico = new CustomLabel("Médico", style.letraSize);
    CustomLabel lblPaciente = new CustomLabel("Paciente", style.letraSize);
    CustomLabel lblFechaHora = new CustomLabel("Fecha y hora", style.letraSize);
    CustomLabel lblEstado = new CustomLabel("Estado", style.letraSize);

    //Botón
    CustomButton btnAgendarCita = new CustomButton("Agendar nueva cita");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    Espaciador espacioh1 = new Espaciador(100, 10);
    Espaciador espacioh2 = new Espaciador(150, 10);
    Espaciador espacioh3 = new Espaciador(80, 10);
    Espaciador espacioh4 = new Espaciador(80, 10);

    public boolean x = false;

    public PnlCitas() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //pnlCitas = this;


        add(espaciov1);

        //Encabezado
        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.add(espacioh1);
        columnas.add(lblMedico);
        columnas.add(espacioh2);
        columnas.add(lblPaciente);
        columnas.add(espacioh3);
        columnas.add(lblFechaHora);
        columnas.add(espacioh4);
        columnas.add(lblEstado);

        add(columnas);


        //----------PLACEHOLDER HARDCODEADO----------
        PnlElementoCita ejemplo = new PnlElementoCita(this);
        add(ejemplo);
        //----------FIN DE PLACEHOLDER----------


        //----------LÓGICA AQUÍ----------
        /*

        for(int i = 0; i < citas.length; i++){
            PnlElementoCita elementoCita = new PnlElementoCita(citas);
            add(elementoCita)
        }

        */
        //----------FIN DE LÓGICA----------


        btnAgendarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DlgRegistrarCita registrar = new DlgRegistrarCita(null, pnlCitas);
                registrar.setVisible(true);


            }
        });
        add(btnAgendarCita);

        setOpaque(testeoColor);
        setVisible(true);

    }

    public void refresh() {
        /*
        DlgRegistrarCita registrar = new DlgRegistrarCita(null);
        registrar.setVisible(true);*/

        System.out.println("refresh pnlCitas");

        revalidate();
        repaint();
    }
}
