package main;

import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner con = new Scanner(System.in);

        UserDaoInter userDaoInter = new UserDaoImpl();
        System.out.println(userDaoInter.getAllUsers());
    }
}
