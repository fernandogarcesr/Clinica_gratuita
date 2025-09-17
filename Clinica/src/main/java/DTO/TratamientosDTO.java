/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class TratamientosDTO {
    private String descripcion;
    private String duracion;
    private String medicamentos;
    private int id_cita;

    public TratamientosDTO() {
    }
    
    
    public TratamientosDTO(String descripcion, String duracion, String medicamentos, int id_cita) {
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.medicamentos = medicamentos;
        this.id_cita = id_cita;
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
        return "TratamientosDominios{" + ", descripcion=" + descripcion + ", duracion=" + duracion + ", medicamentos=" + medicamentos + ", id_cita=" + id_cita + '}';
    }

}
