package view;

import model.Booking;
import model.Contract;

import java.util.ArrayList;
import java.util.Queue;

public interface IContractView extends View<Contract> {

    void display(Queue<Contract> contracts);


    Contract getDetail(Booking booking);
}
