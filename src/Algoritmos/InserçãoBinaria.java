package Algoritmos;

import Abstract.Inserção;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

public class InserçãoBinaria extends Inserção
{

    public InserçãoBinaria()
    {
    }

    public InserçãoBinaria(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public InserçãoBinaria(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int i, pos, j, k, s;

        for (i = 1; i < lista.size(); ++i)
        {
            j = i - 1;
            s = lista.get(i).getInfo();
            pos = busca_binaria(s, 0, j);

            while (j >= pos)
            {
                lista.get(j+1).setInfo(lista.get(j--).getInfo());
            }
            lista.get(j+1).setInfo(s);
        }

    }

    private int busca_binaria(int item, int inicio, int fim)
    {
        if (fim <= inicio)
            return (lista.get(inicio) != null && item > lista.get(inicio).getInfo()) ? (inicio + 1) : inicio;

        int meio = (inicio + fim) / 2;

        if (item == lista.get(meio).getInfo())
            return meio + 1;
        
        if (item > lista.get(meio).getInfo())
            return busca_binaria(item, meio + 1, fim);
        
        return busca_binaria(item, inicio, meio - 1);
    }

    @Override
    protected void sortedVetor()
    {
        int pos, temp;
        for (int i = 1; i < TL; i++)
        {
            pos = busca_binaria(vet[i], TL);
            for (int j = i; j > pos; j--)
            {
                temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
            }
        }
    }

    private int busca_binaria(int chave, int tl)
    {
        int inicio = 0, fim = TL - 1, meio = fim / 2;
        while (meio != inicio && chave != vet[meio])
        {
            if (chave > meio)
            {
                inicio = meio;
            } else
            {
                fim = meio;
            }
            meio = fim - inicio;
        }
        if (chave > vet[meio])
        {
            return meio + 1;
        }
        return meio;
    }

    @Override
    protected void sortedArquivo()
    {
        int tl = arquivo.size();
        RegistroArq aux = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        int pos;
        for (int i = 1; i < tl; i++)
        {
            arquivo.seekArq(i);
            registro.incMovProg();
            aux.leDoArq(arquivo.getFile());
            pos = busca_binariaArquivo(aux, i);
            for (int j = i; j > pos; j--)
            {
                arquivo.seekArq(j - 1);
                vet.leDoArq(arquivo.getFile());
                arquivo.seekArq(j);
                vet.gravaNoArq(arquivo.getFile());
                registro.sumMovProg(2);//2 seeks
            }
            arquivo.seekArq(pos);
            aux.gravaNoArq(arquivo.getFile());
            registro.incMovProg();
        }
    }

    private int busca_binariaArquivo(RegistroArq elem, int tl)
    {
        int ini = 0, fim = tl - 1, meio = tl / 2;
        RegistroArq vet = new RegistroArq();
        arquivo.seekArq(meio);
        registro.incMovProg();
        vet.leDoArq(arquivo.getFile());

        while (ini < fim && elem.getCodigo() != vet.getCodigo())
        {
            registro.incCompProg();
            if (elem.getCodigo() < vet.getCodigo())
            {
                fim = meio - 1;
            } else
            {
                ini = meio + 1;
            }

            meio = (ini + fim) / 2;

            arquivo.seekArq(meio);
            registro.incMovProg();
            vet.leDoArq(arquivo.getFile());
        }
        registro.incCompProg();
        if (elem.getCodigo() < vet.getCodigo())
        {
            return meio;
        }
        return meio + 1;
    }

}
