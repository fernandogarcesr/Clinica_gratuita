/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominios;

/**
 * Clase de tratamiento dominio usado como una clase POJO, estas clases solo están
 * usadas para regresar el objeto desde la base de datos
 *
 */
public class TratamientoDominio {
    private int id_tratamiento;
    private String descripcion;
    private String duracion;
    private String medicamentos;
    private int id_cita;

    public TratamientoDominio() {
    }
    
    
    public TratamientoDominio(int id_tratamiento, String descripcion, String duracion, String medicamentos, int id_cita) {
        this.id_tratamiento = id_tratamiento;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.medicamentos = medicamentos;
        this.id_cita = id_cita;
    }

    public int getId_tratamiento() {
        return id_tratamiento;
    }

    public void setId_tratamiento(int id_tratamiento) {
        this.id_tratamiento = id_tratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    @Override
    public String toString() {
        return "TratamientosDominios{" + "id_tratamiento=" + id_tratamiento + ", descripcion=" + descripcion + ", duracion=" + duracion + ", medicamentos=" + medicamentos + ", id_cita=" + id_cita + '}';
    }

    
}
