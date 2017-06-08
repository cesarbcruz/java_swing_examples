/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testesswing.exemplo1;

/**
 *
 * @author cesar
 */
public class Presenter {

    MainFrame mainFrame;
    ProgressDialog dialog;

    public Presenter() {
        mainFrame = new MainFrame(this);
        dialog = new ProgressDialog(mainFrame, true);
    }

    public void open() {
        mainFrame.setVisible(true);
    }

    public void executeProcess() {

        mainFrame.setVisible(true);

        Thread t = new Thread(new Runnable() {
            public void run() {
                dialog.getBtnClose().setVisible(false);
                dialog.clearTable();
                initModelTable();
                dialog.setVisible(true);
            }
        });
        t.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    process(i);
                    updateUI(i);
                }
                dialog.getBtnClose().setVisible(true);
            }
        });
        t2.start();
    }

    private void updateUI(int line) {
        dialog.getTblProgress().setValueAt("Enviado", line, 1);
    }

    private void initModelTable() {
        for (int i = 0; i < 50; i++) {
            dialog.addRow(new Object[]{i, "Aguarde..."});
        }
    }

    public static void main(String[] args) {
        new Presenter().open();
    }

    private void process(int i) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Process: " + i);
    }

}
