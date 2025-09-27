/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Dominios.ENUM.Estado;

import java.sql.Date;

/**
 *
 * Clases DTO (data trasfer object) usada para guardar en la base de datos ya
 * que esta no contiene un id, pero usa los datos necesarios para este
 */
public class CitaDTO {

    private String motivo;
    private Date fechaHora;
    private Estado estado;
    private int id_doctor;
    private int id_paciente;

    public CitaDTO(String motivo, Date fechaHora, Estado estado, int id_doctor, int id_paciente) {
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.id_doctor = id_doctor;
        this.id_paciente = id_paciente;
    }

    public CitaDTO() {
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdDoctor() {
        return id_doctor;
    }

    public void setIdDoctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getIdPaciente() {
        return id_paciente;
    }

    public void setIdPaciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    @Override
    public String toString() {
        return "CitasDominio{" + ", motivo=" + motivo + ", fechaHora=" + fechaHora + ", estado=" + estado + ", id_doctor=" + id_doctor + ", id_paciente=" + id_paciente + '}';
    }

}
