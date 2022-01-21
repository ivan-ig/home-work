package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.atm.Banknote;

import java.util.List;

public class Cassette <E extends Banknote> {

    private final List<E> banknotes;

    public Cassette(List<E> banknotes) {
        this.banknotes = banknotes;
    }

    public int getCountBanknotes() {
        return banknotes.size();
    }
}