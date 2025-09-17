/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominios;

import Dominios.ENUM.Sexo;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class PacientesDominio {

    private int id_paciente;
    private String nombre;
    private int edad;
    private Sexo sexo;
    private String direccion;
    private String telefono;
    private String email;

    public PacientesDominio() {
    }

    public PacientesDominio(int id_paciente, String nombre, int edad, Sexo sexo, String direccion, String telefono, String email) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
