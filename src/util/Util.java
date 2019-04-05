package util;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

public class Util
{

    private static final int TF = 2000;

    public static boolean geraArquivoRandomico()
    {
        Random r = new Random();
        boolean flag = true;
        String path = "src\\Arquivos\\fileRandom.dat";
        File file = new File(path);
        try
        {
            if (!file.exists())
            {
                flag = file.createNewFile();
            }
            if (flag)
            {
                RandomAccessFile a = new RandomAccessFile(path, "rw");
                a.setLength(0);
                RegistroArq ra = new RegistroArq();
                for (int i = 1024; i > 0; i--)
                {
                    ra.setCodigo(r.nextInt(1024));
                    ra.gravaNoArq(a);
                }
                a.close();
            }
        } catch (Exception ex)
        {
            flag = false;
            System.out.println(ex.getCause());
        }
        return flag;
    }

    public static boolean geraArquivoOrdenado()
    {
        boolean flag = true;
        String path = "src\\Arquivos\\fileOrdenado.dat";
        File file = new File(path);
        try
        {
            if (!file.exists())
            {
                flag = file.createNewFile();
            }
            if (flag)
            {
                RandomAccessFile a = new RandomAccessFile(path, "rw");
                a.setLength(0);
                RegistroArq ra = new RegistroArq();
                for (int i = 0; i < 1024; i++)
                {
                    ra.setCodigo(i);
                    ra.gravaNoArq(a);
                }
                a.close();
            }
        } catch (Exception ex)
        {
            flag = false;
            System.out.println(ex.getCause());
        }
        return flag;
    }

    public static boolean geraArquivoReverso()
    {
        boolean flag = true;
        String path = "src\\Arquivos\\fileReverso.dat";
        File file = new File(path);
        try
        {
            if (!file.exists())
            {
                flag = file.createNewFile();
            }
            if (flag)
            {
                RandomAccessFile a = new RandomAccessFile(path, "rw");
                a.setLength(0);
                RegistroArq ra = new RegistroArq();
                for (int i = 1024; i > 0; i--)
                {
                    ra.setCodigo(i);
                    ra.gravaNoArq(a);
                }
                a.close();
            }
        } catch (Exception ex)
        {
            flag = false;
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    //Vetores
    public static int[] geraVetorReverso(Integer tf)
    {
        int[] vet = new int[TF];
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            vet[i] = t - i;
        }
        return vet;
    }

    public static int[] geraVetorOrdenado(Integer tf)
    {
        int[] vet = new int[TF];
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            vet[i] = i + 1;
        }
        return vet;
    }

    public static int[] geraVetorRandomico(Integer tf)
    {
        Random r = new Random();
        int[] vet = new int[TF];
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            vet[i] = r.nextInt(TF);
        }
        return vet;
    }

    //Listas
    public static Lista geraListaReverso(Integer tf)
    {
        Lista l = new Lista();
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            l.insereFinal(t - i);
        }
        return l;
    }

    public static Lista geraListaOrdenado(Integer tf)
    {
        Lista l = new Lista();
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            l.insereFinal(i);
        }
        return l;
    }

    public static Lista geraListaRandomico(Integer tf)
    {
        Random r = new Random();
        Lista l = new Lista();
        int t = (tf != null) ? tf : TF;
        for (int i = 0; i < t; i++)
        {
            l.insereFinal(r.nextInt(TF));
        }
        return l;
    }
    public static void swapArquivo(RegistroArq r1, RegistroArq r2, Arquivo a, int index1, int index2)
    {
        a.seekArq(index1);
        r2.gravaNoArq(a.getFile());
        a.seekArq(index2);
        r1.gravaNoArq(a.getFile());
    }
    
    public static void swap(int i, int j, Arquivo arquivo)
    {
        RegistroArq temp = new RegistroArq();
        RegistroArq rj = new RegistroArq();
        arquivo.seekArq(i);
        temp.leDoArq(arquivo.getFile());
        arquivo.seekArq(j);
        rj.leDoArq(arquivo.getFile());
        arquivo.seekArq(i);
        rj.gravaNoArq(arquivo.getFile());
        arquivo.seekArq(j);
        temp.gravaNoArq(arquivo.getFile());
    }
}
