package Presentacion.paneles;

import Presentacion.dialogs.registro.DlgRegistrarCita;
import Presentacion.dialogs.registro.DlgRegistrarTratamiento;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlTratamientos extends JPanel {


    Style style = new Style();
    boolean testeoColor = false;

    //Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Tratamientos", 24);

    //Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.GREEN, false);
    CustomLabel lblMedicamento = new CustomLabel("Medicamento", style.letraSize);
    CustomLabel lblDuracion = new CustomLabel("Duración estimada", style.letraSize);
    CustomLabel lblCita = new CustomLabel("Cita de origen", style.letraSize);

    //Botón
    CustomButton btnRecetarTratamiento = new CustomButton("Recetar nuevo tratamiento");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    int espacioX = 100;
    Espaciador espacioh1 = new Espaciador(100, 10);
    Espaciador espacioh2 = new Espaciador(150, 10);
    Espaciador espacioh3 = new Espaciador(80, 10);


    public PnlTratamientos() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(espaciov1);

        //Encabezado
        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.add(espacioh1);
        columnas.add(lblMedicamento);
        columnas.add(espacioh2);
        columnas.add(lblDuracion);
        columnas.add(espacioh3);
        columnas.add(lblCita);

        add(columnas);


        //Campo de pruebas
        PnlElementoTratamiento ejemplo = new PnlElementoTratamiento();
        add(ejemplo);

        //----------LÓGICA AQUÍ----------
        //cambiar este int por el array.length del control con los dtos
        /*int x = 3; //int de ejemplo, cambiar
        for(int i = 0; i < x; i++){
            //PnlElementoTratamiento elementoTratamiento = new PnlElementoTratamiento(tratamiento);
            //add(elementoTratamiento)
        }
         */

        btnRecetarTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarTratamiento registrar = new DlgRegistrarTratamiento(null);
                registrar.setVisible(true);
            }
        });
        add(btnRecetarTratamiento);

        setOpaque(false);
        setVisible(true);
    }
}
