//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * Keyboard Scanner Class                                                                                      *
 * Provides the functionality for keyboard                                                                     *
 * Currently implements: single instance of keyboard                                                           *
 * ************************************************************************************************************* */

import java.util.Scanner;

public class KeyboardScanner {

    private static Scanner input;

    private KeyboardScanner() {}

    public static Scanner getKeyboard()
    {
        if (input == null)
            input = new Scanner(System.in);

        return input;
    }

}
