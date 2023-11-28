package lk.ijse.connect.service;

public interface BoardUI { // BoardUI is a interface so there are no concrete methods allowed

    public void update(int col,boolean isHuman); // also In Java, interface methods are implicitly public, so it is not necessary to use the public access modifier when declaring methods within an interface.
    public void notifyWinner(Winner winner);
}
