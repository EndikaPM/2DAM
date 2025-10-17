
package conversor;


import Model.ModeloMoneda;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        try {
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            ModeloMoneda monedaModelo = new ModeloMoneda();
            monedaModelo.setMonedaRepository(monedarepositoryImpl);
            MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedaModelo.addMoneda(monedaPrueba);
            System.out.println(monedarepositoryImpl.ObtenerListaMonedas().size());
            //monedarepositoryImpl.deleteMoneda(8);
            System.out.println(monedaModelo.ObtenerListaMonedas().size());
            monedaPrueba.setNombre("Holassssss");
            monedaPrueba.setMultiplicador(2.0F);
            monedaPrueba.setCodigo(22);
            //monedarepositoryImpl.editMoneda(monedaPrueba);
            //System.out.println(monedarepositoryImpl.lastId() + " last id");

            for(MonedaVO mon : monedaModelo.ObtenerListaMonedas()) {
                System.out.println(mon.getCodigo() + " " + mon.getNombre() + ' ' + mon.getMultiplicador());
            }
        } catch (ExcepcionMoneda e) {
            System.out.println(e.imprimirMensaje());
        }

    }
}
