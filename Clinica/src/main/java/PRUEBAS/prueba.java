/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PRUEBAS;

import DAO.PacienteDAO;
import DTO.PacienteDTO;
import Dominios.ENUM.Sexo;
import Dominios.PacienteDominio;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class prueba {

    public static void main(String[] args) {
        PacienteDAO pdao = new PacienteDAO();

       
        PacienteDTO paciente = new PacienteDTO("Fernando", "Garces", "Rodriguez", 22, "Masculino", "Calle california #234", "6441345674", "Fercho@gmail.com");

        pdao.insert(paciente);
        
        System.out.println("Paciente insertado con exito!!" + paciente.getNombre());
       
    }

}
