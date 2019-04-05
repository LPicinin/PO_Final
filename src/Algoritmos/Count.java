package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import java.util.Arrays;
import util.Lista;
import util.No;
import util.RegistroArq;

public class Count extends Algoritmo
{

    public Count(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Count(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int max = lista.max();
        int min = lista.min();
        int size = lista.size();
        int range = max - min + 1;
        No aux;

        int count[] = new int[range];

        Lista saida = new Lista();

        try
        {
            for (int i = 0; i < size; i++)//inica lista
            {
                saida.insereFinal(0);
            }
            aux = lista.getInicio();
            for (int i = 0; i < size; i++, aux = lista.avanca(aux, 1))
            {
                count[aux.getInfo() - min]++;
            }

            for (int i = 1; i < count.length; i++)
            {
                count[i] += count[i - 1];
            }
            aux = lista.getFim();
            for (int i = size - 1; i >= 0; i--)
            {
                saida.set(count[aux.getInfo() - min] - 1, aux.getInfo());
                count[aux.getInfo() - min]--;
                aux = lista.recua(aux, 1);
            }
            aux = lista.getInicio();
            No auxSaida = saida.getInicio();
            while (aux != null && auxSaida != null)
            {
                aux.setInfo(auxSaida.getInfo());
                aux = lista.avanca(aux, 1);
                auxSaida = lista.avanca(auxSaida, 1);
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    //https://www.geeksforgeeks.org/counting-sort/
    @Override
    protected void sortedVetor()
    {
        int max = Arrays.stream(vet).max().getAsInt();
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

    @Override
    protected void sortedArquivo()
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
