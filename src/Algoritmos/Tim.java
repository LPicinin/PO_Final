package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import java.util.Arrays;
import util.Arquivo;
import util.Lista;
import util.No;
import util.RegistroArq;

public class Tim extends Algoritmo
{

    private static final int RUN = 32;

    public Tim()
    {
    }

    public Tim(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Tim(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        for (int i = 0; i < lista.size(); i += RUN)
        {
            insertionSort(lista, i, Math.min((i + 31), (arquivo.size())));
        }
        Lista laux = lista.copyOf(0, lista.size());

        for (int size = RUN; size < lista.size(); size = 2 * size)
        {
            for (int left = 0; left < lista.size(); left += 2 * size)
            {
                int right = Math.min((left + 2 * size - 1), (arquivo.size()));
                merge(laux, left, right);
            }
        }
        lista = laux.copyOf(0, laux.size());
    }

    private void merge(Lista aux, int esq, int dir)
    {
        if (esq < dir)
        {
            int meio = (esq + dir) / 2;
            merge(aux, esq, meio);
            merge(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }

    private void fusao(Lista aux, int ini1, int fim1, int ini2, int fim2)
    {
        int c = 0, i = ini1, j = ini2;
        while (i <= fim1 && j <= fim2)
        {
            if (lista.get(i).getInfo() < lista.get(j).getInfo())
            {
                aux.get(c++).setInfo(lista.get(i++).getInfo());
            } else
            {
                aux.get(c++).setInfo(lista.get(j++).getInfo());
            }
        }
        while (i <= fim1)
        {
            aux.get(c++).setInfo(lista.get(i++).getInfo());
        }
        while (j <= fim2)
        {
            aux.get(c++).setInfo(lista.get(j++).getInfo());
        }
        for (i = 0; i < c; i++)
        {
            lista.get(ini1 + i).setInfo(aux.get(i).getInfo());
        }
    }

    private void insertionSort(Lista l, int ini, int fim)
    {
        No i, pos;
        int aux;
        for (i = l.getInicio().getProx(); i != null; i = i.getProx())
        {
            for (pos = i, aux = i.getInfo(); pos != l.getInicio() && aux < pos.getAnt().getInfo(); pos = pos.getAnt())
            {
                pos.setInfo(pos.getAnt().getInfo());
            }
            pos.setInfo(aux);
        }
    }
///////////////////////////////////////vetor

    @Override
    protected void sortedVetor()
    {
        for (int i = 0; i < TL; i += RUN)
        {
            insertionSort(vet, i, Math.min((i + 31), (TL)));
        }
        int[] auxv = Arrays.copyOf(vet, vet.length);//auxiliar

        for (int size = RUN; size < TL; size = 2 * size)
        {
            for (int left = 0; left < TL; left += 2 * size)
            {
                int right = Math.min((left + 2 * size - 1), (TL - 1));
                merge(auxv, left, right);
            }
        }
        vet = Arrays.copyOf(auxv, auxv.length);
    }

    private void insertionSort(int[] vet, int ini, int fim)
    {
        int n = fim;
        int key, j;
        for (int i = ini; i < n; ++i)
        {
            key = vet[i];
            j = i - 1;
            while (j >= 0 && vet[j] > key)
            {
                vet[j + 1] = vet[j];
                j = j - 1;
            }
            vet[j + 1] = key;
        }
    }

    //pega i e pega atrás
    private void merge(int[] aux, int esq, int dir)
    {
        if (esq < dir)
        {
            int meio = (esq + dir) / 2;
            merge(aux, esq, meio);
            merge(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }

    private void fusao(int[] aux, int ini1, int fim1, int ini2, int fim2)
    {
        int c = 0, i = ini1, j = ini2;
        while (ini1 < fim1 && ini2 < fim2)
        {
            if (vet[ini1] < vet[ini2])
            {
                aux[c++] = vet[ini1++];//vet[c++] = vet[ini1++];
            } else
            {
                aux[c++] = vet[ini2++];//vet[c++] = vet[ini2++];
            }
        }
        while (i < fim1)
        {
            aux[c++] = vet[i++];
        }
        while (j < fim2)
        {
            aux[c++] = vet[j++];
        }
        for (i = 0; i < c; i++)
        {
            vet[ini1 + i] = aux[i];
        }
    }

    //////////////////////arquivo
    @Override
    protected void sortedArquivo()
    {
        for (int i = 0; i < arquivo.size(); i += RUN)
        {
            insertionSort(i, Math.min((i + 31), (TL)));
        }

        Arquivo auxv = new Arquivo("src\\Arquivos\\AuxMerge.dat");

        for (int size = RUN; size < arquivo.size(); size = 2 * size)
        {
            for (int left = 0; left < arquivo.size(); left += 2 * size)
            {
                int right = Math.min((left + 2 * size - 1), (arquivo.size() - 1));
                merge(auxv, left, right);
            }
        }
        //vet = Arrays.copyOf(auxv, auxv.length);

    }

    protected void insertionSort(int ini, int fim)
    {
        int tl = fim;
        int pos;
        RegistroArq aux = new RegistroArq();
        RegistroArq vet = new RegistroArq();
        for (int i = ini; i < tl; i++)
        {
            pos = i;
            registro.incMovProg();
            arquivo.seekArq(i);
            aux.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(pos - 1);
            vet.leDoArq(arquivo.getFile());
            
            registro.incCompProg();
            while (pos > 0 && aux.getCodigo() < vet.getCodigo())
            {
                registro.incMovProg();
                arquivo.seekArq(pos);
                vet.gravaNoArq(arquivo.getFile());

                pos--;
                registro.incMovProg();
                arquivo.seekArq(pos - 1);
                vet.leDoArq(arquivo.getFile());
            }
            arquivo.seekArq(pos);
            aux.gravaNoArq(arquivo.getFile());
        }
    }

    private void merge(Arquivo aux, int esq, int dir)
    {
        int meio;
        if (esq < dir)
        {
            meio = (esq + dir) / 2;
            merge(aux, esq, meio);
            merge(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }

    private void fusao(Arquivo aux, int ini1, int fim1, int ini2, int fim2)
    {
        RegistroArq veti = new RegistroArq();
        RegistroArq vetj = new RegistroArq();

        int k = 0, i = ini1, j = ini2;
        while (i <= fim1 && j <= fim2)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            veti.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(j);
            vetj.leDoArq(arquivo.getFile());
            
            registro.incCompProg();
            if (veti.getCodigo() < vetj.getCodigo())
            {
                registro.incMovProg();
                aux.seekArq(k);
                veti.gravaNoArq(aux.getFile());
                i++;
            } else
            {
                registro.incMovProg();
                aux.seekArq(k);
                vetj.gravaNoArq(aux.getFile());
                j++;
            }
            k++;
            //aux[k++] = (vet[i] < vet[j]) ? vet[i++] : vet[j++];
        }

        while (i <= fim1)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            veti.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            aux.seekArq(k);
            veti.gravaNoArq(aux.getFile());
            i++;
            k++;
            //aux[k++] = vet[i++];
        }

        while (j <= fim2)
        {
            registro.incMovProg();
            arquivo.seekArq(j);
            vetj.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            aux.seekArq(k);
            vetj.gravaNoArq(aux.getFile());
            j++;
            k++;
        }

        for (int l = 0; l < k; l++)
        {
            registro.incMovProg();
            aux.seekArq(l);
            veti.leDoArq(aux.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(l + ini1);
            veti.gravaNoArq(arquivo.getFile());
        }

    }
}
