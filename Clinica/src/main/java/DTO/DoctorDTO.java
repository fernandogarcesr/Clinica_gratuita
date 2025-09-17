/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class DoctorDTO {
    
    private String nombre;
    private String especialidad;
    private String telefono;
    private String email;

    public DoctoresDTO() {
    }

    
    public DoctoresDTO( String nombre, String especialidad, String telefono, String email) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    @Override
    public String toString() {
        return "DoctoresDominio{" + ", nombre=" + nombre + ", especialidad=" + especialidad + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
    
    

}
