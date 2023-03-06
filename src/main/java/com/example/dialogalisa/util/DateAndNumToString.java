package com.example.dialogalisa.util;

import java.time.LocalDate;

public class DateAndNumToString {

    public int dayWeakFromString(String commandString){
        if (commandString.contains("завтра")) {
            return  LocalDate.now().getDayOfWeek().getValue() + 1;
        }else if (commandString.contains("сегодня")) {
            return  LocalDate.now().getDayOfWeek().getValue();
        }else if (commandString.contains("понедельник")) {
            return  1;
        }else if (commandString.contains("вторник")) {
            return 2;
        }else if (commandString.contains("среда")) {
            return 3;
        }else if (commandString.contains("четверг")) {
            return 4;
        }else if (commandString.contains("пятница")) {
            return 5;
        }else {
            return LocalDate.now().getDayOfWeek().getValue();
        }
    }

    public String dayWeakToStringDeclensions (int day) {
        if (day == 1) {
            return "Понедельник";
        }else if (day == 2) {
            return "Вторник";
        }else if (day == 3) {
            return "Среду";
        }else if (day == 4) {
            return "Четверг";
        }else if (day == 5) {
            return "Пятницу";
        }else if (day == 6) {
            return "Субботу";
        }else  {
            return "Воскресенье";
        }
    }

    public String dayWeakToString (int day) {
        if (day == 1) {
            return "Понедельник";
        }else if (day == 2) {
            return "Вторник";
        }else if (day == 3) {
            return "Среда";
        }else if (day == 4) {
            return "Четверг";
        }else if (day == 5) {
            return "Пятница";
        }else if (day == 6) {
            return "Суббота";
        }else  {
            return "Воскресенье";
        }
    }

    public String numToStringTts(int num) {
        if (num == 1) {
            return "первый";
        }else if (num ==2) {
            return "второй";
        }else if (num ==3) {
            return "третий";
        }else if (num ==4) {
            return "четвертый";
        }else if (num ==5) {
            return "пятый";
        }else if (num ==6) {
            return "шестой";
        }else {
            return String.valueOf(num);
        }
    }
}
