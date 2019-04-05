package Algoritmos;

import Abstract.Inserção;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

public class InserçãoDireta extends Inserção
{

    public InserçãoDireta()
    {
    }

    public InserçãoDireta(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public InserçãoDireta(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        No i, pos;
        int aux;
        for (i = lista.getInicio().getProx(); i != null; i = i.getProx())
        {
            for (pos = i, aux = i.getInfo(); pos != lista.getInicio() && aux < pos.getAnt().getInfo(); pos = pos.getAnt())
            {
                pos.setInfo(pos.getAnt().getInfo());
            }
            pos.setInfo(aux);
        }
    }

    @Override
    protected void sortedVetor()
    {
        int n = TL;
        for (int i = 1; i < n; ++i)
        {
            int key = vet[i];
            int j = i - 1;
            while (j >= 0 && vet[j] > key)
            {
                vet[j + 1] = vet[j];
                j = j - 1;
            }
            vet[j + 1] = key;
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int tl = arquivo.size();
        int pos;
        RegistroArq aux = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        for (int i = 1; i < tl; i++)
        {
            pos = i;
            arquivo.seekArq(i);
            aux.leDoArq(arquivo.getFile());
            arquivo.seekArq(pos - 1);
            vet.leDoArq(arquivo.getFile());
            registro.sumMovProg(2);//moveu para i e j

            while (pos > 0 && aux.getCodigo() < vet.getCodigo())
            {
                registro.incCompProg();
                arquivo.seekArq(pos);
                vet.gravaNoArq(arquivo.getFile());
                
                pos--;
                arquivo.seekArq(pos - 1);
                vet.leDoArq(arquivo.getFile());
                registro.sumMovProg(2);//permutação
            }
            arquivo.seekArq(pos);
            aux.gravaNoArq(arquivo.getFile());
            registro.incMovProg();
        }
    }

}
