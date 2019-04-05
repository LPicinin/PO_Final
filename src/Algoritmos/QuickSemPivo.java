package Algoritmos;

import Abstract.Quick;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

public class QuickSemPivo extends Quick
{

    public QuickSemPivo()
    {
    }

    public QuickSemPivo(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public QuickSemPivo(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    private void quickSPL(int ini, int fim)
    {
        int i = ini, j = fim;
        int temp;
        No ni, nj;
        while (i < j)
        {
            ni = lista.get(i);
            nj = lista.get(j);
            while (i < j && ni.getInfo() <= nj.getInfo())
            {
                i++;
                ni = ni.getProx();
            }

            temp = nj.getInfo();
            nj.setInfo(ni.getInfo());
            ni.setInfo(temp);

            while (i < j && nj.getInfo() >= ni.getInfo())
            {
                j--;
                nj = nj.getAnt();
            }

            temp = nj.getInfo();
            nj.setInfo(ni.getInfo());
            ni.setInfo(temp);

        }
        if (ini < i - 1)
        {
            quickSP(ini, i - 1);
        }
        if (j + 1 < fim)
        {
            quickSP(j + 1, fim);
        }
    }

    private void quickSP(int ini, int fim)
    {
        int i = ini, j = fim;
        int temp = 0;
        while (i < j)
        {
            while (i < j && vet[i] <= vet[j])
            {
                i++;
            }
            //if(i < j)//em arquivo
            {
                temp = vet[j];
                vet[j] = vet[i];
                vet[i] = temp;
            }
            while (i < j && vet[j] >= vet[i])
            {
                j--;
            }
            //if(i < j)//em arquivo
            {
                temp = vet[j];
                vet[j] = vet[i];
                vet[i] = temp;
            }
        }
        if (ini < i - 1)
        {
            quickSP(ini, i - 1);
        }
        if (j + 1 < fim)
        {
            quickSP(j + 1, fim);
        }
    }

    @Override
    protected void sortedLista()
    {
        quickSPL(0, lista.size() - 1);
    }

    @Override
    protected void sortedVetor()
    {
        sort(0, TL - 1);
    }
    
    int partition(int low, int high) 
    { 
        int pivot = vet[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            if (vet[j] <= pivot) 
            { 
                i++; 
                int temp = vet[i]; 
                vet[i] = vet[j]; 
                vet[j] = temp; 
            } 
        } 
  
        int temp = vet[i+1]; 
        vet[i+1] = vet[high]; 
        vet[high] = temp; 
  
        return i+1; 
    } 
    void sort(int low, int high) 
    { 
        if (low < high) 
        { 
            int pi = partition(low, high); 
  
            sort(low, pi-1); 
            sort(pi+1, high); 
        } 
    } 

    @Override
    protected void sortedArquivo()
    {
        quickSPA(0, arquivo.size() - 1);
    }

    private void quickSPA(int inicio, int fim)
    {
        int i = inicio, j = fim;
        RegistroArq r = new RegistroArq();
        RegistroArq r2 = new RegistroArq();

        while (i < j)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            r.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(j);
            r2.leDoArq(arquivo.getFile());

            registro.incCompProg();
            while (i < j && r.getCodigo() <= r2.getCodigo())
            {
                i++;
                registro.incMovProg();
                arquivo.seekArq(i);
                r.leDoArq(arquivo.getFile());
            }
            if (arquivo.posicoesExistes(i, j))
            {
                registro.incMovProg();
                arquivo.seekArq(i);
                r2.gravaNoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(j);
                r.gravaNoArq(arquivo.getFile());

                registro.incMovProg();
                arquivo.seekArq(i);
                r.leDoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(j);
                r2.leDoArq(arquivo.getFile());
            }

            registro.incCompProg();
            while (i < j && r2.getCodigo() >= r.getCodigo())
            {
                j--;
                registro.incMovProg();
                arquivo.seekArq(j);
                r2.leDoArq(arquivo.getFile());
            }
            if (arquivo.posicoesExistes(i, j))
            {
                registro.incMovProg();
                arquivo.seekArq(i);
                r2.gravaNoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(j);
                r.gravaNoArq(arquivo.getFile());

                registro.incMovProg();
                arquivo.seekArq(i);
                r.leDoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(j);
                r2.leDoArq(arquivo.getFile());
            }
        }
        if (inicio < i - 1)
        {
            quickSPA(inicio, i - 1);
        }
        if (j + 1 < fim)
        {
            quickSPA(j + 1, fim);
        }
    }

}
