/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clases DTO (data trasfer object) usada para guardar en la base de datos ya
 * que esta no contiene un id, pero usa los datos necesarios para este
 */
public class TratamientoDTO {

    private String descripcion;
    private String duracion;
    private String medicamentos;
    private int id_cita;

    public TratamientoDTO() {
    }

    public TratamientoDTO(String descripcion, String duracion, String medicamentos, int id_cita) {
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

    public int getIdCita() {
        return id_cita;
    }

    public void setIdCita(int id_cita) {
        this.id_cita = id_cita;
    }

    @Override
    public String toString() {
        return "TratamientosDominios{" + ", descripcion=" + descripcion + ", duracion=" + duracion + ", medicamentos=" + medicamentos + ", id_cita=" + id_cita + '}';
    }

}
