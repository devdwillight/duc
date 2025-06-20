package view;

import java.util.ArrayList;
import java.util.Scanner;

public interface View<T> {

    void display(ArrayList<T> entities);

    T getDetail();
}
