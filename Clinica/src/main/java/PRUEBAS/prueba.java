/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PRUEBAS;

import DAO.PacienteDAO;
import DTO.PacienteDTO;
import Dominios.ENUM.Sexo;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class prueba {

    public static void main(String[] args) {
        PacienteDAO pdao = new PacienteDAO();

        System.out.println(pdao.insert(new PacienteDTO("Gamma prueba", 20, Sexo.FEMENINO, "5to qlo", "666", "chichis.com"))
        );

    }

}
