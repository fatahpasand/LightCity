package org.example.defualtSystem;

import org.example.models.Food;

import java.sql.SQLException;

public class FastFoodShop {
    Food food = new Food();
    Bank bank = new Bank();
    public Boolean hotdog() throws SQLException {
        food.eat(2);
        if((bank.money() - 0.2) >= 0) {
            bank.modMoney(bank.money() - 0.2);
            return true;
        }else{
            return false;
        }
    }
    public Boolean hamburger() throws SQLException {
        food.eat(4);
        if((bank.money() - 0.4) >= 0) {
            bank.modMoney(bank.money() - 0.4);
            return true;
        }else{
            return false;
        }
    }
    public Boolean pizza() throws SQLException {
        food.eat(6);
        if((bank.money() - 0.4) >= 0) {
            bank.modMoney(bank.money() - 0.4);
            return true;
        }else{
            return false;
        }
    }
}
