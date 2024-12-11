package com.mycompany.ej3_calculadora2;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class CalculadoraController implements Initializable 
{

    @FXML
    private Button b7;
    @FXML
    private Button b6;
    @FXML
    private Button b9;
    @FXML
    private Button b5;
    @FXML
    private Button b8;
    @FXML
    private Button b4;
    @FXML
    private Button b3;
    @FXML
    private Button b2;
    @FXML
    private Button b1;
    @FXML
    private Button borrar;
    @FXML
    private Button punto;
    @FXML
    private Button b0;
    @FXML
    private TextField pantalla;
    @FXML
    private Button resta;
    @FXML
    private Button suma;
    @FXML
    private Button prod;
    @FXML
    private Button div;
    @FXML
    private Button calcular;
    
    private String d1 = "";
    private String d2 = "";
    private String res = "";
    private String op = "";
    private String op2 = "";
    private String dAct = "d1";
    private boolean calc = false;
    
    private String errorinfinito="Dividir entre 0 = infinito";
    private String indeterminacion="Indeterminación";

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {}
    
    /**
     * Este método borra el contenido de todas las variables y de la pantalla
     */
    @FXML
    public void borrar()
    {
        pantalla.setText("");
        d1 = "";
        d2 = "";
        res = "";
        op = "";
        pantalla.setStyle("-fx-text-fill: black");
    }

    /**
     * Selecciona el tipo de operación y el orden en el que se ejecutan
     * @param event 
     */
    @FXML
    private void setOp(ActionEvent event) 
    {
        if (calc == true)
        {
            borrar();
            calc = false;
        }
        // Borra si hay un erro infinito anteriormente
        if(pantalla.getText().equals(errorinfinito))
        {
            borrar();
        }
        Button botonPresionado = (Button) event.getSource();
        String textoBoton = botonPresionado.getText();
        // 1º op, si la primera op y el 2º digito no se ha seleccionado todavía 
        if(op.equals("") && d2.equals(""))
        {    
            op = textoBoton;
            pantalla.setText(pantalla.getText() + "" + op);
            System.out.println("1ª op: " + op + " ");
        }
        // Si ya hay una op seleccionada
        else if(res.equals(""))
        {
            //almacena la cuenta anterior en d1 y borra d2
            calcular2(Double.parseDouble(d1), Double.parseDouble(d2), op);
            d1 = res;
            res = "";
            d2 = "";
            op = textoBoton;
            pantalla.setText(pantalla.getText() + "" + op);
            System.out.println("2ª op: " + op + " d1 cambiado: " + d1 + " ");
        }  
    }

   
    @FXML
    private void addN(ActionEvent event) 
    {
        if (calc == true)
        {
            borrar();
            calc = false;
        }
        if(pantalla.getText().equals(errorinfinito))
        {
            borrar();
        }
        Button botonPresionado = (Button) event.getSource();
        String textoBoton = botonPresionado.getText();
        if(op.equals(""))
        {
            d1 = d1 + "" + textoBoton;
            pantalla.setText("" + d1);
            System.out.println("d1: " + d1 + " ");
        }
        else
        {
            if(d1.equals(res) || res.equals(""))
            {
                d2 = d2 + "" + textoBoton;
                pantalla.setText(pantalla.getText() + "" + d2);
                System.out.println("d2: " + d2 + " ");
            }
        }
    }
    
    @FXML
    private void calcular(ActionEvent event) 
    {      
        if(pantalla.getText().equals(errorinfinito))
        {
            borrar();
        }
        pantalla.setStyle("-fx-text-fill: black");
        switch(op)
        {
            case "+":
                res = (Double.parseDouble(d1) + Double.parseDouble(d2)) + "";
                break;
            case "-":
                res = (Double.parseDouble(d1) - Double.parseDouble(d2)) + "";
                break;
            case "*":
                res = (Double.parseDouble(d1) * Double.parseDouble(d2)) + "";
                break;
            case "/":
                
                try
                {
                    res = (Double.parseDouble(d1) / Double.parseDouble(d2)) + "";
                }catch(ArithmeticException e)
                {
                   //res = "Error";
                        
                }
                if(res.equals("Infinity"))
                {
                    res=errorinfinito;
                    pantalla.setStyle("-fx-text-fill: red"); 
                }
                if(res.equals("NaN"))
                {
                    res=indeterminacion;
                    pantalla.setStyle("-fx-text-fill: red"); 
                }
                //break;
        }
        pantalla.setText(res);
        calc = true;
        
    }
    /**
     * 
     * @param d1
     * @param d2
     * @param op 
     */
     private void calcular2( Double d1, Double d2, String op) 
    {      
        if(pantalla.getText().equals(errorinfinito))
        {
            borrar();
        }
        pantalla.setStyle("-fx-text-fill: black");
        switch(op)
        {
            case "+":
                res = (d1 + d2) + "";
                break;
            case "-":
                res = (d1 - d2) + "";
                break;
            case "*":
                res = (d1 * d2) + "";
                break;
            case "/":
                try
                {
                    res = (d1 / d2) + "";
                }catch(ArithmeticException e)
                {
                   //res = "Error";
                }
                if(res.equals("Infinity"))
                {
                    res=errorinfinito;
                    
                }
                if(res.equals("NaN"))
                {
                    res=indeterminacion;
                    pantalla.setStyle("-fx-text-fill: red"); 
                }
        }
    }
}