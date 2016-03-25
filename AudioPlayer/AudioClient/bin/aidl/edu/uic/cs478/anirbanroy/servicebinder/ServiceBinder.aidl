package edu.uic.cs478.anirbanroy.servicebinder;

interface ServiceBinder {
    void play(in int songID);
    void pause();
    void resume();
    void stop();
    List<String> getAllTransactions();
}