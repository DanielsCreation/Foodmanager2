package com.android.foodmanager2.util;

public class SpinnerHelper {
    public static int helpUnit(String auswahl){
        int i = 0;
        switch (auswahl) {
            case "Gramm":
                i = 0;
                break;
            case "Liter":
                i = 1;
                break;
            case "Stück":
                i = 2;
                break;
            default:
                i = 0;
                break;
        }
        return i;
    }


    public static int helpStorage(String auswahl){
        int i = 0;
        switch (auswahl) {
            case "normale Lagerung":
                i = 0;
                break;
            case "gekühlt lagern":
                i = 1;
                break;
            case "tiefgefroren lagern":
                i = 2;
                break;
            default:
                i = 0;
                break;
        }
        return i;
    }
}