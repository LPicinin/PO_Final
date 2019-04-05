package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Lista;
import util.RegistroArq;

/**
 *
 * @author Aluno
 */
public class SelectSort extends Algoritmo
{
    public SelectSort()
    {
    }

    public SelectSort(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public SelectSort(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }
    @Override
    protected void sortedLista()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void sortedVetor()
    {
        int n = vet.length;
        for (int i = 0; i < n - 1; i++)
        {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
            {
                if (vet[j] < vet[min_idx])
                {
                    min_idx = j;
                }
            }
            int temp = vet[min_idx];
            vet[min_idx] = vet[i];
            vet[i] = temp;
        }
    }

    @Override
    protected void sortedArquivo()
    {
        RegistroArq menor = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        int posMenor;
        int tl = arquivo.size();
        for (int i = 0; i < tl - 1; i++)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            menor.leDoArq(arquivo.getFile());
            posMenor = i;
            for (int j = i + 1; j < tl; j++)
            {
                registro.incMovProg();
                arquivo.seekArq(j);
                vet.leDoArq(arquivo.getFile());
                registro.incCompProg();
                if (menor.getCodigo() > vet.getCodigo())
                {
                    registro.incMovProg();
                    arquivo.seekArq(j);
                    menor.leDoArq(arquivo.getFile());
                    posMenor = j;
                }
            }
            arquivo.seekArq(i);
            vet.leDoArq(arquivo.getFile());
            arquivo.seekArq(posMenor);
            vet.gravaNoArq(arquivo.getFile());
            arquivo.seekArq(i);
            menor.gravaNoArq(arquivo.getFile());
            
            registro.sumCompProg(3);//3 seeks
        }
    }

}
