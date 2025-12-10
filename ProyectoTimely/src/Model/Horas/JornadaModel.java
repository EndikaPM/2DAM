package Model.Horas;

import Model.Repository.Exception.JornadaExeception;
import Model.Repository.Interface.JornadaRepository;
import Model.UserType;
import Model.Usuario.Usuario;

import java.util.ArrayList;

public class JornadaModel {
    private JornadaRepository jornadaRepository;

    public JornadaModel(JornadaRepository jornadaRepository){this.jornadaRepository = jornadaRepository;}

    public ArrayList<Jornada> obtenerListJornadas() throws JornadaExeception {
        return this.jornadaRepository.obtenerListJornadas();
    }

    public boolean addJornada(Jornada jornada) throws JornadaExeception {
        return this.jornadaRepository.addJornada(jornada);
    }

    public boolean addEntradaJornada(Jornada hEntrada) throws JornadaExeception {
        return this.jornadaRepository.addEntradaJornada(hEntrada);
    }

    public boolean addSalidaJornada(Jornada hSalida) throws JornadaExeception {
        return this.jornadaRepository.addSalidaJornada(hSalida);
    }

    public boolean updateJornada(Jornada jornada) throws JornadaExeception {
        return this.jornadaRepository.updateJornada(jornada);
    }

    public boolean deleteJornada(Usuario usuario, int id, String dniUsuario) throws JornadaExeception {
        if (usuario.getUserType() == UserType.Administrador){
            return this.jornadaRepository.deleteJornada(id,dniUsuario);
        }else{
            System.out.println("No es posible eliminar si no eres Administrador");
            return false;
        }

    }

    public int lastId() throws JornadaExeception {
        return this.jornadaRepository.lastId();
    }
}
