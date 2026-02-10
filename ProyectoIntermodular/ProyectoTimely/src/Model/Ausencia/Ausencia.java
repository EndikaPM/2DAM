package Model.Ausencia;

import Model.AusenciaType;
import Model.Usuario.Usuario;

import java.time.LocalDate;

public class Ausencia {
    private int id;
    private Usuario usuarioId;
    private LocalDate diaInicioAusencia;
    private LocalDate diaFinAusencia;
    private AusenciaType motivo;

    public Ausencia(){}
    public Ausencia(int id, Usuario usuarioId, LocalDate diaInicioAusencia, LocalDate diaFinAusencia, AusenciaType motivo){
        this.id = id;
        this.usuarioId = usuarioId;
        this.diaInicioAusencia = diaInicioAusencia;
        this.diaFinAusencia = diaFinAusencia;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDiaFinAusencia() {
        return diaFinAusencia;
    }

    public void setDiaFinAusencia(LocalDate diaFinAusencia) {
        this.diaFinAusencia = diaFinAusencia;
    }

    public LocalDate getDiaInicioAusencia() {
        return diaInicioAusencia;
    }

    public void setDiaInicioAusencia(LocalDate diaInicioAusencia) {
        this.diaInicioAusencia = diaInicioAusencia;
    }
    public AusenciaType getMotivo() {
        return motivo;
    }

    public void setMotivo(AusenciaType motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Ausencia{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", diaInicioAusencia=" + diaInicioAusencia +
                ", diaFinAusencia=" + diaFinAusencia +
                ", motivo=" + motivo +
                '}';
    }
}
