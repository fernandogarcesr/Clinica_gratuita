package Clinica.src.main.java.Presentacion.paneles;

import Presentacion.dialogs.registro.DlgRegistrarPaciente;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlPacientes extends JPanel{

    Style style = new Style();
    boolean testeoColor = false;
    PnlPacientes pnlPacientes = this;

    //Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Pacientes", 24);

    //Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.BLUE, false);
    CustomLabel lblNombre = new CustomLabel("Nombre", style.letraSize);
    CustomLabel lblSexo = new CustomLabel("Sexo", style.letraSize);
    CustomLabel lblEdad = new CustomLabel("Edad", style.letraSize);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", style.letraSize);

    //Botón
    CustomButton btnRegistrarPaciente = new CustomButton("Registrar nuevo paciente");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    int espacioX = 100;
    Espaciador espacioh1 = new Espaciador(300, 10);
    Espaciador espacioh2 = new Espaciador(10, 10);
    Espaciador espacioh3 = new Espaciador(80, 10);


    public PnlPacientes() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(espaciov1);

        //Encabezado
        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.setLayout(new GridLayout(1,6));

        columnas.add(espacioh1);
        columnas.add(lblNombre);
        columnas.add(lblSexo);
        columnas.add(lblEdad);
        columnas.add(lblTelefono);

        add(columnas);


        //----------PLACEHOLDER HARDCODEADO----------
        PnlElementoPaciente ejemplo = new PnlElementoPaciente(pnlPacientes);
        add(ejemplo);
        //----------FIN DE PLACEHOLDER----------


        //----------LÓGICA AQUÍ----------
        /*

        for(int i = 0; i < pacientes.length; i++){
            PnlElementoPaciente elementoPaciente = new PnlElementoPaciente(pnlPacientes, paciente);
            add(elementoPaciente)
        }

         */
        //----------FIN DE LÓGICA----------


        btnRegistrarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarPaciente registrar = new DlgRegistrarPaciente(null, pnlPacientes);
                registrar.setVisible(true);
            }
        });
        add(btnRegistrarPaciente);

        setOpaque(false);
        setVisible(true);
    }

    public void refresh() {

        System.out.println("refresh pnlPacientes");

        revalidate();
        repaint();
    }
}
