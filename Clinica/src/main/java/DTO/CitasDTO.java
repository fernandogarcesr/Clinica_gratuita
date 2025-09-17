/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Dominios.ENUM.Estado;
import java.sql.Date;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class CitasDTO {
    private String motivo;
    private Date fechaHora;
    private Estado estado;
    private int id_doctor;
    private int id_paciente;

    public CitasDTO(String motivo, Date fechaHora, Estado estado, int id_doctor, int id_paciente) {
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.id_doctor = id_doctor;
        this.id_paciente = id_paciente;
    }

    public CitasDTO() {
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

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    @Override
    public String toString() {
        return "CitasDominio{" + ", motivo=" + motivo + ", fechaHora=" + fechaHora + ", estado=" + estado + ", id_doctor=" + id_doctor + ", id_paciente=" + id_paciente + '}';
    }
    
    
}
