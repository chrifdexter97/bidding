package org.bid;

import org.bid.presentation.CommandLineInteractor;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//
//        CommandLineInteractor commandLineInteractor = CommandLineInteractor.getCommandLineInteractor();
//
//        commandLineInteractor.start(in);

        System.out.println(Locale.getDefault());
        System.out.println(DateFormat.getDateInstance(DateFormat.SHORT,Locale.getDefault()));

    }

}