package com.example.bill;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.NumberBinding;

import java.io.IOException;

public class Bill extends Application {
    @Override
    public void start(Stage stage) throws IOException {

    }
    public static void main(String[] args) {

        ClaseBill electricBill1 = new ClaseBill();
        ClaseBill electricBill2 = new ClaseBill();
        ClaseBill electricBill3 = new ClaseBill();
        IntegerProperty num1 = new SimpleIntegerProperty(5);
        IntegerProperty num2 = new SimpleIntegerProperty(10);
        System.out.println("sin vinculo"+num1.getValue());

        num1.bindBidirectional(num2);

        System.out.println("con vinculo"+num1.getValue());

        NumberBinding total = Bindings.add(electricBill1.amountDueProperty()
                .add(electricBill2.amountDueProperty()),
                electricBill3.amountDueProperty());
        total.addListener((new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        System.out.println("la factura es invalida!!");
                        System.out.println("Dentro de la clase "+total.getValue());
                    }
        }
        ));

        total.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                System.out.println("La factura actual es "+ newValue);
            }
        });

        electricBill1.setAmountDue(200.00);

        electricBill2.setAmountDue(100.00);
        electricBill3.setAmountDue(75.00);

        System.out.println(total.getValue());

        electricBill3.setAmountDue(150.00);

        System.out.println(total.getValue());
        System.out.println("------------------------BIDIRECCIONAL----------------------");
        num1.set(5);
        System.out.println("bidereccional el 1:  "+num1.getValue());
        System.out.println("bidereccional el 2:  "+num2.getValue());
        num2.set(8);
        System.out.println("les asignamos valor de 8 en vez de 5 " + num2.getValue());
        System.out.println("les asignamos valor de 8 en vez de 5 " + num1.getValue());

        num2.set(num1.getValue()+ 10);
        System.out.println("sumandoles 10 al uno solo= "+num2.getValue());
        System.out.println("sumandoles 10 al uno solo= "+num1.getValue());
    }
}
