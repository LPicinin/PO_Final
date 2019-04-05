package Algoritmos;

import Abstract.Quick;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;
import util.Util;

public class QuickComPivo extends Quick
{

    public QuickComPivo()
    {
    }

    public QuickComPivo(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public QuickComPivo(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        quickPL(0, lista.size() - 1);
    }

    @Override
    protected void sortedVetor()
    {
        quickP(0, TL - 1);
    }

    private void quickP(int ini, int fim)
    {
        int pivo = vet[(ini + fim) / 2];
        int i = ini, j = fim;
        int temp;
        while (i <= j)
        {
            while (vet[i] < pivo && i < fim)
            {
                i++;
            }
            while (vet[j] > pivo & j > ini)
            {
                j--;
            }
            if (i <= j)
            {
                //Util.swap(vet[i++], vet[j--]);
                temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
                i++;
                j--;
            }
        }
        if (ini < j)
        {
            quickP(ini, j+1);
        }
        if (i < fim)
        {
            quickP(i, fim);
        }
    }

    private void quickPL(int ini, int fim)
    {
        int pivo = lista.get((ini + fim) / 2).getInfo();
        int i = ini, j = fim;
        int temp;
        No auxi, auxj;
        while (i < j)
        {
            auxi = lista.get(i);
            auxj = lista.get(j);
            while (auxi.getInfo() < pivo)
            {
                i++;
                auxi = auxi.getProx();
            }
            while (auxj.getInfo() > pivo)
            {
                j--;
                auxj = auxj.getAnt();
            }
            if (i <= j)
            {
                temp = auxi.getInfo();
                auxi.setInfo(auxj.getInfo());
                auxj.setInfo(temp);
            }
        }
        if (ini < j)
        {
            quickP(ini, j);
        }
        if (i < fim)
        {
            quickP(i, fim);
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int l = 0;
        int h = arquivo.size() - 1;
        int[] stack = new int[h - l + 1];
        int top = -1;
        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0)
        {
            h = stack[top--];
            l = stack[top--];

            int p = particao(l, h);

            if (p - 1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            if (p + 1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    private int particao(int low, int high)
    {
        int i = low - 1;
        RegistroArq pivo = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        registro.incMovProg();
        arquivo.seekArq(high);
        pivo.leDoArq(arquivo.getFile());

        for (int j = low; j < high; j++)
        {
            registro.incMovProg();
            arquivo.seekArq(j);
            vet.leDoArq(arquivo.getFile());
            registro.incCompProg();
            if (vet.getCodigo() <= pivo.getCodigo())
            {
                i++;
                Util.swap(i, j, arquivo);
                registro.sumMovProg(2);
            }
        }
        Util.swap(high, i + 1, arquivo);
        return i + 1;
    }
}
