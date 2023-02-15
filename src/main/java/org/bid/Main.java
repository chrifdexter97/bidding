package org.bid;

import org.bid.presentation.CommandLineInteractor;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        CommandLineInteractor commandLineInteractor = CommandLineInteractor.getCommandLineInteractor();

        commandLineInteractor.start(in);


    }

}