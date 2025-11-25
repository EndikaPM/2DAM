package com.example.ex1ev1.Controller;

public class Total implements InterfaceTotal {
    @Override
    public float calcularTotal(Integer catidad, Float precio) {return catidad*precio;}
}
