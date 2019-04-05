package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import java.util.Arrays;
import util.Arquivo;
import util.Lista;
import util.No;
import util.RegistroArq;

public class Radix extends Algoritmo
{

    public Radix(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Radix(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int m = lista.max();
        for (int exp = 1; m / exp > 0; exp *= 10)
        {
            Count(lista, exp);
        }
    }

    private void Count(Lista l, int exp)
    {
        int size = l.size();
        int range = 10;

        int count[] = new int[range];

        Lista saida = new Lista();

        for (int i = 0; i < size; i++)//inica lista
        {
            saida.insereFinal(0);
        }

        for (int i = 0; i < size; i++)
        {
            count[(l.get(i).getInfo() / exp) % range]++;
        }
        for (int i = 1; i < range; i++)
        {
            count[i] += count[i - 1];
        }
        for (int i = size - 1; i >= 0; i--)
        {
            saida.set(count[(l.get(i).getInfo() / exp) % range] - 1, l.get(i).getInfo());
            count[(l.get(i).getInfo() / exp) % range]--;
        }
        No a1 = l.getInicio();
        No a2 = saida.getInicio();
        while (a2 != null)
        {
            a1.setInfo(a2.getInfo());
            a1 = l.avanca(a1, 1);
            a2 = l.avanca(a2, 1);
        }
    }
/////////////////////////vetor
    @Override
    protected void sortedVetor()
    {
        int m = findMaxVetor();
        for (int exp = 1; m / exp > 0; exp *= 10)
        {
            Count(exp, m);
        }
    }
    private void Count(int exp, int max)
    {
        int min = findMinVetor();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[TL];
        for (int i = 0; i < TL; i++)
        {
            count[vet[i] - min]++;
        }

        for (int i = 1; i < count.length; i++)
        {
            count[i] += count[i - 1];
        }

        for (int i = TL - 1; i >= 0; i--)
        {
            output[count[vet[i] - min] - 1] = vet[i];
            count[vet[i] - min]--;
        }

        for (int i = 0; i < TL; i++)
        {
            vet[i] = output[i];
        }
    }
    ////////////////////arquivo
    @Override
    protected void sortedArquivo()
    {
        int m = arquivo.max();
        for (int exp = 1; m / exp > 0; exp *= 10)
        {
            Count(arquivo, exp);
        }
    }

    private void Count(Arquivo arquivo, int exp)
    {
        int max = arquivo.max();
        int min = arquivo.min();
        
        registro.sumMovProg(arquivo.size() * 2);
        registro.sumCompProg(arquivo.size() * 2);
        int range = max - min + 1;
        int count[] = new int[range];
        int s = arquivo.size();
        int output[] = new int[s];
        RegistroArq r = new RegistroArq();
        for (int i = 0; i < s; i++)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            r.leDoArq(arquivo.getFile());
            count[r.getCodigo() - min]++;
        }

        for (int i = 1; i < count.length; i++)
        {
            count[i] += count[i - 1];
        }

        for (int i = s - 1; i >= 0; i--)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            r.leDoArq(arquivo.getFile());

            output[count[r.getCodigo() - min] - 1] = r.getCodigo();
            count[r.getCodigo() - min]--;
        }

        for (int i = 0; i < s; i++)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            r.setCodigo(output[i]);
            r.gravaNoArq(arquivo.getFile());
        }
    }

}
