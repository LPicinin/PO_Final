/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po;

import Abstract.Algoritmo;
import Algoritmos.*;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import util.Arquivo;
import util.Lista;
import util.Saida;
import util.Util;

/**
 *
 * @author luis
 */
public class TelaPrincipalController implements Initializable
{

    @FXML
    private TableView<Saida> tabelaOrdenada;
    @FXML
    private TableView<Saida> tabelaReversa;
    @FXML
    private TableView<Saida> tabelaRandom;
    @FXML
    private TableColumn<Saida, Integer> nomeOrd;
    @FXML
    private TableColumn<Saida, Integer> compEqualOrd;
    @FXML
    private TableColumn<Saida, Integer> movProgOrd;
    @FXML
    private TableColumn<Saida, Integer> movEquaOrd;
    @FXML
    private TableColumn<Saida, Long> tempoOrd;
    @FXML
    private TableColumn<Saida, Integer> nomeReverso;
    @FXML
    private TableColumn<Saida, Integer> compProgRever;
    @FXML
    private TableColumn<Saida, Integer> compEquaRever;
    @FXML
    private TableColumn<Saida, Integer> movProgRever;
    @FXML
    private TableColumn<Saida, Integer> movEquaRever;
    @FXML
    private TableColumn<Saida, Long> tempoRever;
    @FXML
    private TableColumn<Saida, Integer> nomeRand;
    @FXML
    private TableColumn<Saida, Integer> compProgRand;
    @FXML
    private TableColumn<Saida, Integer> compEquaRand;
    @FXML
    private TableColumn<Saida, Integer> movProgRand;
    @FXML
    private TableColumn<Saida, Integer> movEquaRand;
    @FXML
    private TableColumn<Saida, Long> tempoRand;
    @FXML
    private TableColumn<Saida, Integer> compProgOrd;
    @FXML
    private JFXCheckBox cbPregerados;
    @FXML
    private BorderPane tela;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        iniciaComponentes();
        /*InserçãoBinaria ai = new InserçãoBinaria(Util.geraListaReverso(20), Util.geraVetorReverso(20), 20);
        
        System.out.println(ai.getLista().toString());
        ai.OrdenaLista();
        System.out.println(ai.getLista().toString());
        */
        /*
        System.out.println(Arrays.toString(ai.getVet()));
        ai.OrdenaVetor();
        System.out.println(Arrays.toString(ai.getVet()));
        */
        /*
        Arquivo aReverso = new Arquivo("src\\Arquivos\\fileReverso.dat");
        ai.setArquivo(aReverso);
        ai.OrdenaArquivo();
        */
        /*
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(new StringSelection(aReverso.toString()), null);
        */
        
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                tela.setDisable(true);
                scriptOredacoes();
                tela.setDisable(false);
            }
        }).start();
        
    }
    
    private void scriptOredacoes()
    {
        String name;
        int n = 1024;
        double g = 0.577216;
        Lista l = Util.geraListaRandomico(16);
        int[] v = Util.geraVetorRandomico(16);
        Algoritmo a;
        Arquivo aOrd = new Arquivo("src\\Arquivos\\fileOrdenado.dat");
        Arquivo aReverso = new Arquivo("src\\Arquivos\\fileReverso.dat");
        Arquivo aRandom = new Arquivo("src\\Arquivos\\fileRandom.dat");
        
        //bolha
        iniciaArquivos();
        a = new Buble();///////////////////////////aqui
        name = "Buble";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, 0);
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, (3*(n*n-n))/2);
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, (3*(n*n-n))/4);
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //Bucket
        iniciaArquivos();
        a = new Bucket();///////////////////////////aqui
        name = "Bucket";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        //Comb
        iniciaArquivos();
        a = new Comb();///////////////////////////aqui
        name = "Comb";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        
        //Count
        iniciaArquivos();
        a = new Count();///////////////////////////aqui
        name = "Count";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        //Gnome
        iniciaArquivos();
        a = new Gnome();///////////////////////////aqui
        name = "Gnome";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        //Heap
        iniciaArquivos();
        a = new Heap();///////////////////////////aqui
        name = "Heap";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        

        //InsercaoBinaria
        iniciaArquivos();
        a = new InserçãoBinaria();///////////////////////////aqui
        name = "InserçãoBinaria";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui(0, 3*(n-1));
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*(Math.log10(n)-Math.log10(Math.E)+0.5)), (int)(Math.pow(n, 2)+9*n-10)/4);
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*(Math.log10(n)-Math.log10(Math.E)+0.5)), (int)(Math.pow(n, 2)+3*n-4)/2);
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        //InsercaoDireta
        iniciaArquivos();
        a = new InserçãoDireta();///////////////////////////aqui
        name = "InserçãoDireta";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((n-1), (3*(n-1)));
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(Math.pow(n, 2)+n-2)/4, (int)(Math.pow(n, 2)+9*n-10)/4);
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(Math.pow(n, 2)+n-4)/4,  (int)(Math.pow(n, 2)+3*n-4)/2);
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //MergeP
        iniciaArquivos();
        a = new MergeP();///////////////////////////aqui
        name = "MergeP";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        //MergeS
        iniciaArquivos();
        a = new MergeS();///////////////////////////aqui
        name = "MergeS";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //QuickComPivo
        iniciaArquivos();
        a = new QuickComPivo();///////////////////////////aqui
        name = "QuickComPivo";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        //QuickSemPivo
        iniciaArquivos();
        a = new QuickSemPivo();///////////////////////////aqui
        name = "QuickSemPivo";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //Radix
        iniciaArquivos();
        a = new Radix();///////////////////////////aqui
        name = "Radix";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //SelectSort
        iniciaArquivos();
        a = new SelectSort();///////////////////////////aqui
        name = "SelectSort";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(Math.pow(n, 2)-n)/2, 3*(n-1));
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(Math.pow(n, 2)-n)/2, (int)(n*(Math.log10(n)+g)));       
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, (int)((n*n)/4+(3*(n-1))));
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //Shake
        iniciaArquivos();
        a = new Shake();///////////////////////////aqui
        name = "Shake";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, 0);
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, (3*(n*n-n))/2);
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        a.getRegistro().iniciaEqui((int)(n*n-n)/2, (3*(n*n-n))/4);
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //Shell
        iniciaArquivos();
        a = new Shell();///////////////////////////aqui
        name = "Shell";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
        
        //Tim
        iniciaArquivos();
        a = new Tim();///////////////////////////aqui
        name = "Tim";
        a.setArquivo(aOrd);
        a.OrdenaArquivo();
        tabelaOrdenada.getItems().add(new Saida(name, a.getRegistro()));
        
        a.setArquivo(aRandom);
        a.OrdenaArquivo();
        tabelaRandom.getItems().add(new Saida(name, a.getRegistro()));
        
        
        a.setArquivo(aReverso);
        a.OrdenaArquivo();
        tabelaReversa.getItems().add(new Saida(name, a.getRegistro()));
        
    }
    
    private void iniciaArquivos()
    {
        Util.geraArquivoOrdenado();
        Util.geraArquivoRandomico();
        Util.geraArquivoReverso();
    }
    private void iniciaComponentes()
    {
        nomeOrd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgOrd.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEqualOrd.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgOrd.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaOrd.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoOrd.setCellValueFactory(new PropertyValueFactory<>("tempo"));

        nomeRand.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgRand.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEquaRand.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgRand.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaRand.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoRand.setCellValueFactory(new PropertyValueFactory<>("tempo"));

        nomeReverso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgRever.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEquaRever.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgRever.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaRever.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoRever.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        
        /*ObservableList<Saida> o = FXCollections.observableArrayList();
        o.add(new Saida("Merge", new RegistroOrdenado(0, 0, 0, 0, 0)));
        o.add(new Saida("Merge", new RegistroOrdenado(0, 5, 30, 100, 0)));
        o.add(new Saida("Merge", new RegistroOrdenado(90, 70, 6, 1, 21)));
        tabelaOrdenada.setItems(o);
        */
    }
}
