package Model.Horas;

import Model.Usuario.Usuario;

public class HorasTrabajadas {
    private Usuario id_usuario;
    private int horasContrato;
    private int horasTrabajadas;

    public HorasTrabajadas(Usuario id_usuario, int horasContrato) {
        this.id_usuario = id_usuario;
        this.horasContrato = horasContrato;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getHorasContrato() {
        return horasContrato;
    }

    public void setHorasContrato(int horasContrato) {
        this.horasContrato = horasContrato;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
