package Model;

public class HorasTrabajo {
    Usuario usuarioId;
    Integer horasContrato;
    Integer horasTrabajadas;

    public HorasTrabajo(Usuario usuarioId, Integer horasContrato) {
        this.usuarioId = usuarioId;
        this.horasContrato = horasContrato;
        this.horasTrabajadas = 0;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getHorasContrato() {
        return horasContrato;
    }

    public void setHorasContrato(Integer horasContrato) {
        this.horasContrato = horasContrato;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
