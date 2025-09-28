package Presentacion.paneles;

import Presentacion.dialogs.registro.DlgRegistrarDoctor;
import Presentacion.dialogs.registro.DlgRegistrarPaciente;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlPacientes extends JPanel{

    Style style = new Style();
    boolean testeoColor = false;

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


        //Campo de pruebas
        PnlElementoPaciente ejemplo = new PnlElementoPaciente();
        add(ejemplo);

        //add containerpanel con un for que liste los pacientes
        //----------LÓGICA AQUÍ----------
        //cambiar este int por el array.length del control con los dtos
        /*int x = 3; //int de ejemplo, cambiar
        for(int i = 0; i < x; i++){
            //PnlElementoPaciente elementoPaciente = new PnlElementoPaciente(paciente);
            //add(elementoPaciente)
        }
         */

        btnRegistrarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarPaciente registrar = new DlgRegistrarPaciente(null);
                registrar.setVisible(true);
            }
        });
        add(btnRegistrarPaciente);

        setOpaque(false);
        setVisible(true);
    }
}
