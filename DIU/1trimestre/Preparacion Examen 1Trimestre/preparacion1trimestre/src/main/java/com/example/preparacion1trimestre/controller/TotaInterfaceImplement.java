package com.example.preparacion1trimestre.controller;

public class TotaInterfaceImplement implements TotalInterface {
    @Override
    public float calcularTotal(Integer catidad, Float precio) {return catidad*precio;}
}
