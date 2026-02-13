package Model.Horas;

import Model.Usuario.Usuario;

public class HorasTrabajadas {
    private Usuario id_usuario;
    private float horasContrato;
    private int horasTrabajadas;

    public HorasTrabajadas(Usuario id_usuario, float horasContrato) {
        this.id_usuario = id_usuario;
        this.horasContrato = horasContrato;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public float getHorasContrato() {
        return horasContrato;
    }

    public void setHorasContrato(float horasContrato) {
        this.horasContrato = (float) Math.round(horasContrato * 10) /10;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String toString() {
        return "HorasTrabajadas{" +
                "id_usuario=" + id_usuario +
                ", horasContrato=" + horasContrato +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
