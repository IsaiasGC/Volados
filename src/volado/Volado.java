/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Isaias
 */
public class Volado implements ActionListener{
    
    private final Interfaz interfaz;
    private int moneda;
    private int saldo;
    private int apuesta;
    private String gana="";
    
    public Volado(){
        saldo=30;
        apuesta=10;
        interfaz=new Interfaz(this);
        interfaz.setVisible(true);
        interfaz.setSaldo(saldo, apuesta);
    }
    public void jugar(){
        if(apuesta<=saldo ){
            if(apuesta>=0){
                tirarVolado();
                verificar();
            }
        }else{
            JOptionPane.showMessageDialog(null, "No tiene saldo Suficiente", "Error", 0);
        }
    }
    public void tirarVolado(){
        moneda=getValorMoneda();
        gana="";
        if(moneda==1){
            gana="SOL";
        }else{
            gana="AGUILA";
        }
        interfaz.setResultados(moneda, gana);
    }
    public int getValorMoneda(){
        double num=Math.random();
        int moneda;
        if(num>=0 && num<=0.5)
            moneda=1;
        else
            moneda=2;
        
        return moneda;
    }
    public void verificar(){
        String apostado=interfaz.getApostado();
        
        if(gana.equals(apostado)){
            JOptionPane.showMessageDialog(null, "Has Ganado!!", "FELICIDADES", 1);
            saldo+=apuesta;
        }else{
            JOptionPane.showMessageDialog(null, "Has Perdido", "Lo sentimos", 1);
            saldo-=apuesta;
            apuesta*=2;
        }
        interfaz.limpiarMoneda();
        interfaz.setSaldo(saldo, apuesta);
    }
    public void reiniciar(){
        saldo=30;
        apuesta=10;
        interfaz.setSaldo(this.saldo, this.apuesta);
        interfaz.limpiarMoneda();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Volado();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("jugar")){
            if(interfaz.seleccionado())
                jugar();
            else
                JOptionPane.showMessageDialog(null, "Seleccione a que le apuesta(CHO/HAN)", "Error", 2);
        }else{
            reiniciar();
        }
    }
    
}
