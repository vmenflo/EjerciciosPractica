/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex17;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author victor
 */
public class Empleado {
    
    
    // Atributos
    private String nombreCompleto;
    private String dni;
    private String tipoPersonal;
    private String puestoTrabajo;
    private boolean horarioPerso;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private LocalTime horasIniciales;
    private String totalHoras;
    private boolean activo;
    
    // Constructores
    public Empleado() {
    }

    public Empleado(String nombreCompleto, String dni, String tipoPersonal, 
            String puestoTrabajo, boolean horarioPerso, LocalDate fechaAlta, 
            LocalDate fechaBaja, LocalTime horasIniciales, String totalHoras, boolean activo) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.tipoPersonal = tipoPersonal;
        this.puestoTrabajo = puestoTrabajo;
        this.horarioPerso = horarioPerso;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.horasIniciales = horasIniciales;
        this.totalHoras = totalHoras;
        this.activo = activo;
    }
    
    // Getters y setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public boolean isHorarioPerso() {
        return horarioPerso;
    }

    public void setHorarioPerso(boolean horarioPerso) {
        this.horarioPerso = horarioPerso;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public LocalTime getHorasIniciales() {
        return horasIniciales;
    }

    public void setHorasIniciales(LocalTime horasIniciales) {
        this.horasIniciales = horasIniciales;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    // Equals y hashCode (Solo con el dni porque el enunciado dice que es suficiente)
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        return Objects.equals(this.dni, other.dni);
    }
    
    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("nombreCompleto=").append(nombreCompleto);
        sb.append(", dni=").append(dni);
        sb.append(", tipoPersonal=").append(tipoPersonal);
        sb.append(", puestoTrabajo=").append(puestoTrabajo);
        sb.append(", horarioPerso=").append(horarioPerso);
        sb.append(", fechaAlta=").append(fechaAlta);
        sb.append(", fechaBaja=").append(fechaBaja);
        sb.append(", horasIniciales=").append(horasIniciales);
        sb.append(", totalHoras=").append(totalHoras);
        sb.append(", activo=").append(activo);
        sb.append('}');
        return sb.toString();
    }
    
}
