/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominios;

import Dominios.ENUM.Estado;
import java.sql.Date;

/**
 * Clase de Cita dominio usado como una clase POJO, estas clases solo están
 * usadas para regresar el objeto desde la base de datos
 *
 */
public class CitaDominio {

    private int id_citas;
    private String motivo;
    private Date fechaHora;
    private Estado estado;
    private int id_doctor;
    private int id_paciente;

    public CitaDominio(int id_citas, String motivo, Date fechaHora, Estado estado, int id_doctor, int id_paciente) {
        this.id_citas = id_citas;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.id_doctor = id_doctor;
        this.id_paciente = id_paciente;
    }

    public CitaDominio() {
    }

    public int getId_citas() {
        return id_citas;
    }

    public void setId_citas(int id_citas) {
        this.id_citas = id_citas;
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
        return "CitasDominio{" + "id_citas=" + id_citas + ", motivo=" + motivo + ", fechaHora=" + fechaHora + ", estado=" + estado + ", id_doctor=" + id_doctor + ", id_paciente=" + id_paciente + '}';
    }

}
