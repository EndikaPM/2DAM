package Model.Empresa;

public class Empresa {

    private String nifEmpresa;
    private String nomEmpresa;
    private String direEmpresa;

    public Empresa(){}
    public Empresa(String nifEmpresa, String nomEmpresa, String direEmpresa) {
        this.nifEmpresa = nifEmpresa;
        this.nomEmpresa = nomEmpresa;
        this.direEmpresa = direEmpresa;
    }

    public String getDireEmpresa() {
        return direEmpresa;
    }

    public void setDireEmpresa(String direEmpresa) {
        this.direEmpresa = direEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    @Override
    public String toString() {
        return "\n\t\t\tEmpresa{" +
                "\n\t\t\t\tnifEmpresa='" + nifEmpresa + '\'' +
                "\n\t\t\t\tnomEmpresa='" + nomEmpresa + '\'' +
                "\n\t\t\t\tdireEmpresa='" + direEmpresa + '\'' +
                "\n\t\t}";
    }
}
