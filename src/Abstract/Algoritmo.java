package Abstract;

import java.util.Arrays;
import util.Arquivo;
import util.Lista;
import util.RegistroArq;

public abstract class Algoritmo
{

    protected Lista lista;

    protected int[] vet;

    protected int TL;

    protected Arquivo arquivo;

    protected RegistroArq reg_ar;

    protected Registro registro;

    public Algoritmo()
    {

    }

    public Algoritmo(Lista lista, int[] vet, int TL, Registro registro)
    {
        this.lista = lista;
        this.vet = vet;
        this.TL = TL;
        this.registro = registro;
    }

    public Algoritmo(Lista lista, int[] vet, int TL)
    {
        this.lista = lista;
        this.vet = vet;
        this.TL = TL;
    }

    public final void OrdenaLista()
    {
        long ini = System.currentTimeMillis();
        sortedLista();
        registro.setTempo(System.currentTimeMillis() - ini);
    }

    public final void OrdenaVetor()
    {
        long ini = System.currentTimeMillis();
        sortedVetor();
        registro.setTempo(System.currentTimeMillis() - ini);
    }

    public final void OrdenaArquivo()
    {
        long ini = System.currentTimeMillis();
        sortedArquivo();
        registro.setTempo(System.currentTimeMillis() - ini);
    }

    public Lista getLista()
    {
        return lista;
    }

    public void setLista(Lista lista)
    {
        this.lista = lista;
    }

    public int[] getVet()
    {
        return vet;
    }

    public void setVet(int[] vet)
    {
        this.vet = vet;
    }

    public int getTL()
    {
        return TL;
    }

    public void setTL(int TL)
    {
        this.TL = TL;
    }

    public Arquivo getArquivo()
    {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo)
    {
        this.arquivo = arquivo;
    }

    public RegistroArq getReg_ar()
    {
        return reg_ar;
    }

    public void setReg_ar(RegistroArq reg_ar)
    {
        this.reg_ar = reg_ar;
    }

    public Registro getRegistro()
    {
        return registro;
    }

    public void setRegistro(Registro registro)
    {
        this.registro = registro;
    }

    public String toStringVetor()
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < TL; i++)
        {
            s.append(vet[i]).append(", ");
        }
        return s.toString();
    }

    protected int findMinVetor()
    {
        Integer min = (TL > 0) ? vet[0] : null;
        for (int i = 0; i < TL; i++)
        {
            if (vet[i] < min)
            {
                min = vet[i];
            }
        }
        return min;
    }

    protected int findMaxVetor()
    {
        return Arrays.stream(vet).max().getAsInt();
    }

    protected abstract void sortedLista();

    protected abstract void sortedVetor();

    protected abstract void sortedArquivo();
}
